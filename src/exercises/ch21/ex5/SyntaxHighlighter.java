package exercises.ch21.ex5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SyntaxHighlighter {
    private final Set<String> keywords = Set.of("abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null");
    private final File input;
    private final File output;

    public SyntaxHighlighter(String inputPath, String outputPath) {
        this.input = new File(inputPath);
        this.output = new File(outputPath);
    }

    public void makeHTML() {
        ArrayList<String> content = new ArrayList<>();
        content.add("""
                <html>
                <style>
                    .keyword {
                        color: navy;
                    }
                    .comment {
                        color: green;
                    }
                    .string {
                        color: blue;
                    }
                </style>
                                        
                <boby>
                    <pre>""");
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                if (s.startsWith("//")) {
                    content.add(String.format("<b class=\"comment\">%s</b>", s));
                    continue;
                } else if (s.contains("//")) {
                    s = s.replace("//", "<b class=\"comment\">//");
                    s += "</b>";
                }
                List<String> words = new ArrayList<>();
                boolean insideString = false;
                for (String word : s.split(" ")) {
                    if (word.contains("class=\"")) {
                        words.add(word);
                        continue;
                    }
                    int stringStart = word.indexOf("\"");
                    if (stringStart >= 0) {
                        int stringEnd = word.indexOf("\"", stringStart + 1);
                        if (stringEnd >= 0) {
                            word = String.format(
                                    "%s<b class=\"string\">%s</b>%s",
                                    word.substring(0, stringStart),
                                    word.substring(stringStart, stringEnd + 1),
                                    word.substring(stringEnd + 1)
                            );
                        } else {
                            if (insideString) {
                                word = String.format(
                                        "%s</b>%s",
                                        word.substring(0, stringStart + 1),
                                        word.substring(stringStart + 1)
                                );
                                insideString = false;
                            } else {
                                word = String.format(
                                        "%s<b class=\"string\">%s",
                                        word.substring(0, stringStart),
                                        word.substring(stringStart)
                                );
                                insideString = true;
                            }
                        }
                    }
                    if (keywords.contains(word)) {
                        words.add(String.format("<b class=\"keyword\">%s</b>", word));
                        continue;
                    }
                    words.add(word);
                }
                content.add(String.join(" ", words));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        content.add("""
                    </pre>
                </boby>
                </html>
                """);
        try {
            Files.write(output.toPath(), content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
