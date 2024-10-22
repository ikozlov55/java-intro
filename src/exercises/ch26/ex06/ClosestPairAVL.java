package exercises.ch26.ex06;

import exercises.ch22.ex07.Pair;
import exercises.ch26.base.AVLTree;
import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairAVL {
    public static Pair getClosestPair(Point2D[] points) {
        Point2D[] pointsOrderedOnX = Arrays.stream(points)
                .sorted(Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY))
                .toArray(Point2D[]::new);
        AVLTree<Point2D> tree = new AVLTree<>(Comparator.comparing(Point2D::getY));
        double minDist = Double.POSITIVE_INFINITY;
        Pair result = null;
        for (Point2D current : pointsOrderedOnX) {
            int left = 0;
            while (current.getX() - pointsOrderedOnX[left].getX() > minDist) {
                tree.remove(pointsOrderedOnX[left]);
                left++;
            }

            for (Point2D p : tree) {
                if (p.getY() > current.getY() + minDist || p.getY() < current.getY() - minDist) {
                    continue;
                }
                if (current.distance(p) < minDist) {
                    minDist = current.distance(p);
                    result = new Pair(p, current);
                }
            }
            tree.insert(current);
        }


        return result;
    }
}
