package exercises.ch9.ex10;


public class QuadraticEquation {
    private final double a;
    private final double b;
    private final double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double getRoot1() {
        double D = getDiscriminant();
        if (D >= 0) {
            return (-b + Math.sqrt(D)) / (2 * a);
        } else {
            return 0;
        }
    }

    public double getRoot2() {
        double D = getDiscriminant();
        if (D >= 0) {
            return (-b - Math.sqrt(D)) / (2 * a);
        } else {
            return 0;
        }
    }
}
