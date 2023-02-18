package exercises.ch10.ex12;

import exercises.ch10.ex4.MyPoint;

public class Line2D {
    private MyPoint p1;
    private MyPoint p2;


    public Line2D(MyPoint p1, MyPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public MyPoint getP1() {
        return p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    public double getLength() {
        return p1.distance(p2);
    }

    public MyPoint intersectionPoint(Line2D line) {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        double x3 = line.p1.getX();
        double y3 = line.p1.getY();
        double x4 = line.p2.getX();
        double y4 = line.p2.getY();
        double a = y1 - y2;
        double b = -(x1 - x2);
        double c = y3 - y4;
        double d = -(x3 - x4);
        double e = (y1 - y2) * p1.getX() - (p1.getX() - x2) * y1;
        double f = (y3 - y4) * x3 - (x3 - x4) * y3;

        if (a * d - b * c == 0) {
            return null;
        } else {
            double x = (e * d - b * f) / (a * d - b * c);
            double y = (a * f - e * c) / (a * d - b * c);
            return new MyPoint(x, y);
        }
    }

    public boolean onLineSegment(MyPoint p) {
        double d = (p2.getX() - p1.getX()) * (p.getY() - p1.getY()) - (p.getX() - p1.getX()) * (p2.getY() - p1.getY());
        return d == 0 && p1.distance(p) < p1.distance(p2) && p2.distance(p) < p1.distance(p2);
    }

    public boolean intersects(Line2D line) {
        MyPoint intersectionPoint = intersectionPoint(line);
        return intersectionPoint != null && onLineSegment(intersectionPoint);
    }

}
