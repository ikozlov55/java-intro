package exercises.ch10.ex22;

import java.util.Arrays;

public class MyString1 {
    private char[] chars;

    public MyString1(char[] chars) {
        this.chars = chars;
    }

    public char charAt(int index) {
        return chars[index];
    }

    public int length() {
        return chars.length;
    }

    public MyString1 substring(int begin, int end) {
        char[] substring = new char[end - begin];
        int index = 0;
        for (int i = begin; i < end; i++) {
            substring[index] = chars[i];
            index++;
        }
        return new MyString1(substring);
    }

    public MyString1 toLowerCase() {
        char[] lowerCased = Arrays.copyOf(chars, length());
        for (int i = 0; i < length(); i++) {
            if (Character.isLetter(lowerCased[i])) {
                lowerCased[i] = Character.toLowerCase(lowerCased[i]);
            }
        }
        return new MyString1(lowerCased);
    }

    public boolean equals(MyString1 s) {
        if (s.length() != length()) return false;
        for (int i = 0; i < length(); i++) {
            if (chars[i] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static MyString1 valueOf(int i) {
        return new MyString1(Integer.toString(i).toCharArray());
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}
