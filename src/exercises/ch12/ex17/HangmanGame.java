package exercises.ch12.ex17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class HangmanGame {

    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    ArrayList<String> words = new ArrayList<>();
    private String word;
    private boolean[] guessed;

    public HangmanGame(String path) throws FileNotFoundException {
        File file = new File(path);
        try (
                Scanner scanner = new Scanner(file)
        ) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        }
    }

    public void start() {
        while (true) {
            word = generateWord();
            guessed = new boolean[word.length()];
            int missed = 0;
            while (!allGuessed()) {
                System.out.printf("(Guess) Enter a letter in word %s > ", getWord());
                char letter = scanner.next().charAt(0);
                boolean isMiss = true;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter && guessed[i]) {
                        System.out.printf("%c is already in the word\n", letter);
                        isMiss = false;
                        break;
                    }
                    if (word.charAt(i) == letter) {
                        guessed[i] = true;
                        isMiss = false;
                    }
                }
                if (isMiss) {
                    missed++;
                    System.out.printf("%c is not in the word\n", letter);
                }
            }
            System.out.printf("The word is %s. You missed %d %s\n", getWord(), missed, missed == 1 ? "time" : "times");
            System.out.print("Do you want to guess another word? Enter y or n> ");
            char input = scanner.next().charAt(0);
            if (input == 'y') {
                start();
            } else {
                break;
            }
        }
    }

    private String generateWord() {
        return words.get((int) (Math.random() * words.size()));
    }

    private String getWord() {
        char[] result = word.toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (!guessed[i]) {
                result[i] = '*';
            }
        }

        return String.valueOf(result);
    }

    private boolean allGuessed() {
        for (boolean b : guessed) {
            if (!b) return false;
        }
        return true;
    }
}
