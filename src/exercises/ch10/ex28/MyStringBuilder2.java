package exercises.ch10.ex28;


public class MyStringBuilder2 {
    private char[] buffer;

    public MyStringBuilder2() {
        buffer = new char[0];
    }

    public MyStringBuilder2(char[] chars) {
        buffer = chars;
    }

    public MyStringBuilder2(String s) {
        buffer = s.toCharArray();
    }

    public int length() {
        return buffer.length;
    }

    public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
        char[] newBuffer = new char[length() + s.length()];
        for (int i = 0; i < offset; i++) {
            newBuffer[i] = buffer[i];
        }
        for (int i = offset, j = 0; j < s.length(); i++, j++) {
            newBuffer[i] = s.buffer[j];
        }
        for (int i = offset + s.length(), j = offset; i < newBuffer.length; i++, j++) {
            newBuffer[i] = buffer[j];
        }

        return new MyStringBuilder2(newBuffer);
    }

    public MyStringBuilder2 reverse() {
        char[] newBuffer = new char[length()];
        for (int i = 0, j = length() - 1; i < length(); i++, j--) {
            newBuffer[i] = buffer[j];
        }
        return new MyStringBuilder2(newBuffer);
    }

    public MyStringBuilder2 substring(int begin) {
        char[] newBuffer = new char[length() - begin];
        for (int i = 0, j = begin; i < newBuffer.length; i++, j++) {
            newBuffer[i] = buffer[j];
        }
        return new MyStringBuilder2(newBuffer);
    }

    public MyStringBuilder2 toUpperCase() {
        char[] newBuffer = new char[length()];
        for (int i = 0; i < length(); i++) {
            newBuffer[i] = Character.toUpperCase(buffer[i]);
        }
        return new MyStringBuilder2(String.valueOf(newBuffer));
    }

    public String toString() {
        return String.valueOf(buffer);
    }


}
