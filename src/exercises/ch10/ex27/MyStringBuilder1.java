package exercises.ch10.ex27;


import java.util.Arrays;

/*
    (Implement the StringBuilder class) The StringBuilder class is provided
    in the Java library. Provide your own implementation for the following methods
    (name the new class MyStringBuilder1):
    public MyStringBuilder1(String s);
    public MyStringBuilder1 append(MyStringBuilder1 s);
    public MyStringBuilder1 append(int i);
    public int length();
    public char charAt(int index);
    public MyStringBuilder1 toLowerCase();
    public MyStringBuilder1 substring(int begin, int end);
    public String toString();
 */
public class MyStringBuilder1 {
    private final char[] buffer;

    public MyStringBuilder1(String s) {
        buffer = s.toCharArray();
    }

    public MyStringBuilder1 append(MyStringBuilder1 s) {
        char[] newBuffer = Arrays.copyOf(buffer, buffer.length + s.buffer.length);
        int copyIndex = 0;
        for (int i = length(); i < newBuffer.length; i++) {
            newBuffer[i] = s.buffer[copyIndex];
            copyIndex++;
        }
        return new MyStringBuilder1(String.valueOf(newBuffer));
    }

    public MyStringBuilder1 append(int i) {
        return this.append(new MyStringBuilder1(Integer.toString(i)));
    }

    public int length() {
        return buffer.length;
    }

    public char charAt(int index) {
        return buffer[index];
    }

    public MyStringBuilder1 toLowerCase() {
        char[] newBuffer = new char[length()];
        for (int i = 0; i < length(); i++) {
            newBuffer[i] = Character.toLowerCase(buffer[i]);
        }
        return new MyStringBuilder1(String.valueOf(newBuffer));
    }

    public MyStringBuilder1 substring(int begin, int end) {
        char[] newBuffer = Arrays.copyOfRange(buffer, begin, end);
        return new MyStringBuilder1(String.valueOf(newBuffer));
    }

    public String toString() {
        return String.valueOf(buffer);
    }

}
