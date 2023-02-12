package exercises.ch9.ex1;

public class Rectangle {
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
        return String.format(
                "width: %.2f\nheight: %.2f\narea: %.2f\nperimeter: %.2f",
                width, height, getArea(), getPerimeter()
        );
    }
}
