package exercises.ch20.ex7;

import java.util.ArrayList;
import java.util.stream.Collectors;

class HangManGame {
    private final String word;
    private final boolean[] guessed;
    private final ArrayList<Character> missed;
    private int triesLeft;

    public HangManGame(int triesLeft) {
        String[] words = {"cat", "tree", "book", "teapot", "bottle"};
        word = words[(int) (Math.random() * words.length)];
        guessed = new boolean[word.length()];
        missed = new ArrayList<>();
        this.triesLeft = triesLeft;
    }

    public String getWord(boolean masked) {
        char[] result = word.toCharArray();
        for (int i = 0; i < result.length; i++) {
            if (!guessed[i] && masked) {
                result[i] = '*';
            }
        }

        return new String(result);
    }

    public String getMissed() {
        return missed.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    public boolean allGuessed() {
        for (boolean b : guessed) {
            if (!b) return false;
        }
        return true;
    }

    public void guess(char c) {
        if (triesLeft == 0 || missed.contains(c)) {
            return;
        }
        if (!word.contains(Character.toString(c))) {
            missed.add(c);
            triesLeft--;
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                guessed[i] = true;
            }
        }
    }
}
