package exercises.ch10.ex13;


public class MyRectangle2D {
    private double x;
    private double y;
    private double width;
    private double height;

    public MyRectangle2D() {
        x = 0;
        y = 0;
        width = 1;
        height = 1;
    }

    public MyRectangle2D(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return (width + height) * 2;
    }

    public boolean contains(double x, double y) {
        return (x >= this.x - width / 2 &&
                x <= this.x + width / 2 &&
                y >= this.y - height / 2 &&
                y <= this.y + height / 2);
    }

    public boolean contains(MyRectangle2D r) {
        double x1 = r.x - r.width / 2;
        double y1 = r.y + r.height / 2;
        double x2 = x1;
        double y2 = r.y - r.height / 2;
        double x3 = r.x + r.width / 2;
        double y3 = y1;
        double x4 = x3;
        double y4 = y2;
        return contains(x1, y1) && contains(x2, y2) && contains(x3, y3) && contains(x4, y4);
    }

    public boolean overlaps(MyRectangle2D r) {
        double x1 = r.x - r.width / 2;
        double y1 = r.y + r.height / 2;
        double x2 = x1;
        double y2 = r.y - r.height / 2;
        double x3 = r.x + r.width / 2;
        double y3 = y1;
        double x4 = x3;
        double y4 = y2;
        return contains(r) || contains(x1, y1) || contains(x2, y2) || contains(x3, y3) || contains(x4, y4);
    }


}
