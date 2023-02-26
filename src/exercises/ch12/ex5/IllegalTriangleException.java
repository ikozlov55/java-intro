package exercises.ch12.ex5;

public class IllegalTriangleException extends Exception {
    public IllegalTriangleException() {
        super("In a triangle, the sum of any two sides is greater than the other side.");
    }
}
