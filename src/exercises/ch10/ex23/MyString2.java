package exercises.ch10.ex23;

import java.util.Arrays;

/*
    (Implement the String class) The String class is provided in the Java library.
    Provide your own implementation for the following methods (name the new
    class MyString2):
    public MyString2(String s);
    public int compare(String s);
    public MyString2 substring(int begin);
    public MyString2 toUpperCase();
    public char[] toChars();
    public static MyString2 valueOf(boolean b);
 */
public class MyString2 {
    private final char[] chars;

    public MyString2(String s) {
        chars = s.toCharArray();
    }

    /*
    This is the definition of lexicographic ordering. If two strings are different,
    then either they have different characters at some index that is a valid index for both strings,
    or their lengths are different, or both. If they have different characters at one or more index
    positions, let k be the smallest such index; then the string whose character at position k has
    the smaller value, as determined by using the < operator, lexicographically precedes the other
    string. In this case, compareTo returns the difference of the two character values at position
    k in the two string -- that is, the value:
    this.charAt(k)-anotherString.charAt(k)

    If there is no index position at which they differ, then the shorter string lexicographically
    precedes the longer string. In this case, compareTo returns the difference of the lengths
    of the strings -- that is, the value:
    this.length()-anotherString.length()
    */
    public int compare(String s) {
        char[] otherChars = s.toCharArray();
        if (chars.length != otherChars.length) {
            return chars.length - otherChars.length;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != otherChars[i]) {
                return chars[i] - otherChars[i];
            }
        }
        return 0;
    }

    public MyString2 substring(int begin) {
        char[] substring = new char[chars.length - begin];
        int index = 0;
        for (int i = begin; i < chars.length; i++) {
            substring[index] = chars[i];
            index++;
        }
        return new MyString2(String.valueOf(substring));
    }

    public MyString2 toUpperCase() {
        char[] upperCased = Arrays.copyOf(chars, chars.length);
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(upperCased[i])) {
                upperCased[i] = Character.toUpperCase(upperCased[i]);
            }
        }
        return new MyString2(String.valueOf(upperCased));
    }

    public char[] toChars() {
        return chars;
    }

    public static MyString2 valueOf(boolean b) {
        return new MyString2(b ? "true" : "false");
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}
