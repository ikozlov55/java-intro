package exercises.ch22.ex07;

import javafx.geometry.Point2D;

public class Pair {
    private Point2D p1;
    private Point2D p2;

    public Pair(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public double getDistance() {
        return p1.distance(p2);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", p1, p2);
    }
}
