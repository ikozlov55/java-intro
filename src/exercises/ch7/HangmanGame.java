package exercises.ch7;

import java.util.Locale;
import java.util.Scanner;

class HangmanGame {

    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private String word;
    private boolean[] guessed;

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
        String[] words = {"cat", "tree", "book", "teapot", "bottle"};
        return words[(int) (Math.random() * words.length)];
    }

    private String getWord() {
        char[] result = word.toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (!guessed[i]) {
                result[i] = '*';
            }
        }

        return new String(result);
    }

    private boolean allGuessed() {
        for (boolean b : guessed) {
            if (!b) return false;
        }
        return true;
    }
}
