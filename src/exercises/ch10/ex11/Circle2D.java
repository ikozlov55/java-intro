package exercises.ch10.ex11;


public class Circle2D {
    private final double x;
    private final double y;
    private final double radius;

    public Circle2D() {
        x = 0;
        y = 0;
        radius = 1;
    }

    public Circle2D(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean contains(double x, double y) {
        return distance(x, y) < radius;
    }

    public boolean contains(Circle2D circle) {
        return (distance(circle) + circle.getRadius()) < radius;
    }

    public boolean overlaps(Circle2D circle) {
        double centersDistance = distance(circle);
        return centersDistance > radius && centersDistance < (radius + circle.getRadius());
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public double distance(Circle2D circle) {
        return Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
    }

    @Override
    public String toString() {
        return String.format("Circle2D(%.2f. %.2f)\n", x, y) +
                String.format("Radius: %.2f\n", getRadius()) +
                String.format("Area: %.2f\n", getArea()) +
                String.format("Perimeter: %.2f\n", getPerimeter());
    }
}
