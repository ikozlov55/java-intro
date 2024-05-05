package exercises.ch10.ex3;


public class MyInteger {
    private final int value;

    public static boolean isEven(int value) {
        return new MyInteger(value).isEven();
    }

    public static boolean isOdd(int value) {
        return new MyInteger(value).isOdd();
    }

    public static boolean isPrime(int value) {
        return new MyInteger(value).isPrime();
    }

    public static boolean isEven(MyInteger value) {
        return value.isEven();
    }

    public static boolean isOdd(MyInteger value) {
        return value.isOdd();
    }

    public static boolean isPrime(MyInteger value) {
        return value.isPrime();
    }

    public static MyInteger parseInt(String string) {
        return new MyInteger(Integer.parseInt(string));
    }

    public static MyInteger parseInt(char[] chars) {
        return MyInteger.parseInt(String.valueOf(chars));
    }

    public int getValue() {
        return value;
    }

    public MyInteger(int value) {
        this.value = value;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public boolean isOdd() {
        return !isEven();
    }

    public boolean isPrime() {
        if (value <= 1) return false;
        for (int divisor = 2; divisor <= value / 2; divisor++) {
            if (value % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(int other) {
        return value == other;
    }

    public boolean equals(MyInteger other) {
        return value == other.value;
    }

}
