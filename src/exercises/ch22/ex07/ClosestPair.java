package exercises.ch22.ex07;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ClosestPair {
    /**
     * Return the distance of the closest pair of points
     */
    public static Pair getClosestPair(double[][] points) {
        Point2D[] points2D = Arrays.stream(points)
                .map(p -> new Point2D(p[0], p[1]))
                .toArray(Point2D[]::new);
        return getClosestPair(points2D);
    }

    /**
     * Return the distance of the closest pair of points
     */
    public static Pair getClosestPair(Point2D[] points) {
        Point2D[] pointsOrderedOnX = Arrays.stream(points)
                .sorted(Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY))
                .toArray(Point2D[]::new);
        Point2D[] pointsOrderedOnY = Arrays.stream(points)
                .sorted(Comparator.comparing(Point2D::getY))
                .toArray(Point2D[]::new);

        return distance(pointsOrderedOnX, 0, points.length - 1, pointsOrderedOnY);
    }

    public static Pair getClosestPairBruteForce(Point2D[] points) {
        if (points.length == 2) {
            return new Pair(points[0], points[1]);
        }
        Pair result = null;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                if (result == null || distance(points[i], points[j]) < result.getDistance()) {
                    result = new Pair(points[i], points[j]);
                }
            }
        }

        return result;
    }

    /**
     * Return the distance of the closest pair of points
     * in pointsOrderedOnX[low..high]. This is a recursive
     * method. pointsOrderedOnX and pointsOrderedOnY are
     * not changed in the subsequent recursive calls.
     */
    public static Pair distance(Point2D[] pointsOrderedOnX, int low, int high, Point2D[] pointsOrderedOnY) {
        int n = high - low + 1;
        if (n <= 3) {
            return getClosestPairBruteForce(Arrays.copyOfRange(pointsOrderedOnX, low, high + 1));
        }
        int midIndex = n / 2 + low - 1;
        Point2D mid = pointsOrderedOnX[midIndex];
        System.out.printf("low: %d, high: %d, n: %d, midIndex: %d\n", low, high, n, midIndex);
        Pair p1 = distance(pointsOrderedOnX, low, midIndex, pointsOrderedOnY);
        Pair p2 = distance(pointsOrderedOnX, midIndex + 1, high, pointsOrderedOnY);
        double d1 = p1.getDistance();
        double d2 = p2.getDistance();
        double d = Math.min(d1, d2);

        List<Point2D> stripL = new ArrayList<>();
        List<Point2D> stripR = new ArrayList<>();
        for (Point2D p : pointsOrderedOnY) {
            // in S1
            if (p.getX() < mid.getX() || (p.getX() == mid.getX() && p.getY() <= mid.getY())) {
                if (mid.getX() - p.getX() <= d) {
                    stripL.add(p);
                }
            }
            // in S2
            else if (p.getX() >= mid.getX()) {
                if (p.getX() - mid.getX() <= d) {
                    stripR.add(p);
                }
            }
        }
        Pair p3 = null;
        int r = 0; // r is the index of a point in stripR
        for (Point2D p : stripL) {
            // Skip the points in stripR below p.y − d
            while (r < stripR.size() && stripR.get(r).getY() <= p.getY() - d) {
                r++;
            }
            int r1 = r;
            while (r1 < stripR.size() && Math.abs(stripR.get(r).getY() - p.getY()) <= d) {
                // Check if (p, q[r1]) is a possible closest pair
                if (distance(p, stripR.get(r1)) < d) {
                    d = distance(p, stripR.get(r1));
                    p3 = new Pair(p, stripR.get(r1));
                }
                r1++;
            }
        }

        if (p3 == null) {
            return Stream.of(p1, p2).min(Comparator.comparing(Pair::getDistance)).get();
        } else {
            return Stream.of(p1, p2, p3).min(Comparator.comparing(Pair::getDistance)).get();
        }
    }


    /**
     * Compute the distance between two points p1 and p2
     */
    public static double distance(Point2D p1, Point2D p2) {
        return p1.distance(p2);
    }

    /**
     * Compute the distance between points (x1, y1) and (x2, y2)
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
