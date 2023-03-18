package exercises.ch13.geometric;

public class Rectangle extends GeometricObject {
    double width = 1;
    double height = 1;

    public Rectangle() {
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return height * width;
    }

    public double getPerimeter() {
        return (width + height) * 2;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "\nwidth: %.2f\nheight: %.2f\narea: %.2f\nperimeter: %.2f",
                width, height, getArea(), getPerimeter()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        return getArea() == ((Rectangle) obj).getArea();
    }
}