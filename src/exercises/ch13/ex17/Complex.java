package exercises.ch13.ex17;

public class Complex implements Cloneable, Comparable<Complex> {
    private final double a;
    private final double b;

    public Complex() {
        a = 0;
        b = 0;
    }

    public Complex(double a) {
        this.a = a;
        b = 0;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getRealPart() {
        return a;
    }

    public double getImaginaryPart() {
        return b;
    }

    public Complex add(Complex other) {
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        return new Complex(a + c, b + d);
    }

    public Complex subtract(Complex other) {
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        return new Complex(a - c, b - d);
    }

    public Complex multiply(Complex other) {
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        return new Complex(a * c - b * d, b * c + a * d);
    }

    public Complex divide(Complex other) {
        double c = other.getRealPart();
        double d = other.getImaginaryPart();
        return new Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d));
    }

    public double abs() {
        return Math.sqrt(a * a + b * b);
    }

    @Override
    public String toString() {
        String s = String.format("%.2f", a);
        if (b != 0) {
            s += String.format(" + %.2fi", b);
        }
        return s;
    }

    @Override
    public Complex clone() {
        try {
            return (Complex) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Complex other) {
        return Double.compare(abs(), other.abs());
    }
}
