package exercises.ch10.ex12;

import exercises.ch10.ex4.MyPoint;

/*
    (Geometry: the Triangle2D class) Define the Triangle2D class that contains:
    ■ Three points named p1, p2, and p3 of the type MyPoint with getter and
    setter methods. MyPoint is defined in Programming Exercise 10.4.
    ■ A no-arg constructor that creates a default triangle with the points (0, 0),
    (1, 1), and (2, 5).
    ■ A constructor that creates a triangle with the specified points.
    ■ A method getArea() that returns the area of the triangle.
    ■ A method getPerimeter() that returns the perimeter of the triangle.
    ■ A method contains(MyPoint p) that returns true if the specified point
    p is inside this triangle (see Figure 10.22a).
    ■ A method contains(Triangle2D t) that returns true if the specified
    triangle is inside this triangle (see Figure 10.22b).
    ■ A method overlaps(Triangle2D t) that returns true if the specified
    triangle overlaps with this triangle (see Figure 10.22c).
    Draw the UML diagram for the class and then implement the class. Write a
    test program that creates a Triangle2D object t1 using the constructor
    new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3),
    new MyPoint(5, 3.5)), displays its area and perimeter, and displays the
    result of t1.contains(3, 3), r1.contains(new Triangle2D(new
    MyPoint(2.9, 2), new MyPoint(4, 1), MyPoint(1, 3.4))), and t1
    .overlaps(new Triangle2D(new MyPoint(2, 5.5), new MyPoint
    (4, –3), MyPoint(2, 6.5))).
    (Hint: For the formula to compute the area of a triangle, see Programming
    Exercise 2.19. To detect whether a point is inside a triangle, draw three
    dashed lines, as shown in Figure 10.23. Let Δ denote the area of a triangle.
    If ΔABp + ΔACp + ΔBCp == ΔABC, the point p is inside the triangle, as
    shown in Figure 10.23a. Otherwise, point p is not inside the triangle, as
    shown in Figure 10.23b.
 */
public class Triangle2D {
    private MyPoint p1;
    private MyPoint p2;
    private MyPoint p3;

    public Triangle2D() {
        p1 = new MyPoint(0, 0);
        p2 = new MyPoint(1, 1);
        p3 = new MyPoint(2, 5);
    }

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public MyPoint getP1() {
        return p1;
    }

    public void setP1(MyPoint p1) {
        this.p1 = p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    public void setP2(MyPoint p2) {
        this.p2 = p2;
    }

    public MyPoint getP3() {
        return p3;
    }

    public void setP3(MyPoint p3) {
        this.p3 = p3;
    }

    public double getArea() {
        double side1 = p1.distance(p2);
        double side2 = p2.distance(p3);
        double side3 = p3.distance(p1);
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double getPerimeter() {
        return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
    }

    public boolean contains(MyPoint p) {
        Triangle2D t1 = new Triangle2D(p1, p2, p);
        Triangle2D t2 = new Triangle2D(p2, p3, p);
        Triangle2D t3 = new Triangle2D(p3, p1, p);
        return t1.getArea() + t2.getArea() + t3.getArea() == getArea();
    }

    public boolean contains(Triangle2D t) {
        return t.contains(p1) && t.contains(p2) && t.contains(p3);
    }

    public boolean overlaps(Triangle2D t) {
        Line2D side1 = new Line2D(p1, p2);
        Line2D side2 = new Line2D(p2, p3);
        Line2D side3 = new Line2D(p3, p1);
        Line2D tSide1 = new Line2D(t.p1, t.p2);
        Line2D tSide2 = new Line2D(t.p2, t.p3);
        Line2D tSide3 = new Line2D(t.p3, t.p1);

        return contains(t) || tSide1.intersects(side1) || tSide1.intersects(side2) || tSide1.intersects(side3) ||
                tSide2.intersects(side1) || tSide2.intersects(side2) || tSide2.intersects(side3) ||
                tSide3.intersects(side1) || tSide3.intersects(side2) || tSide3.intersects(side3);
    }
}
