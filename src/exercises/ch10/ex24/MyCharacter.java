package exercises.ch10.ex24;

public class MyCharacter {
    private char value;

    public MyCharacter() {
    }

    public MyCharacter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(char c) {
        return value == c;
    }

    public boolean equals(MyCharacter c) {
        return value == c.getValue();
    }

    public int compare(MyCharacter c) {
        return value - c.getValue();
    }
}
