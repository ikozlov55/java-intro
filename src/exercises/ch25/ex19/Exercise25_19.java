package exercises.ch25.ex19;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Exercise25_19 {
    public static void run(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise25_18 sourcefile targetfile");
            System.exit(1);
        }
        Map<Character, String> codes = new HashMap<>();
        String encoded = "";
        try (FileInputStream fis = new FileInputStream(args[0]);
             ObjectInputStream input = new ObjectInputStream(fis)
        ) {
            codes = (Map<Character, String>) input.readObject();
            StringBuilder builder = new StringBuilder();
            for (byte b : fis.readAllBytes()) {
                String s = Integer.toBinaryString(b);
                if (s.length() > 8) {
                    s = s.substring(s.length() - 8);
                }
                builder.append(s);
            }
            encoded = builder.toString();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        String decoded = decode(encoded, codes);
        try {
            Files.writeString(Path.of(args[1]), decoded);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static String decode(String encoded, Map<Character, String> codes) {
        Map<String, Character> chars = new HashMap<>();
        for (Map.Entry<Character, String> e : codes.entrySet()) {
            chars.put(e.getValue(), e.getKey());
        }
        StringBuilder decoded = new StringBuilder();
        String code = "";
        for (char c : encoded.toCharArray()) {
            code += c;
            if (chars.containsKey(code)) {
                decoded.append(chars.get(code));
                code = "";
            }
        }

        return decoded.toString();
    }
}
