package exercises.utils;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class UtilsFx {
    public static Group arrowLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        double distance = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        double lineAngle;
        double addAngle;
        if (startY > endY) {
            lineAngle = Math.asin((endX - startX) / distance);
            addAngle = Math.PI * 0.75;
        } else {
            lineAngle = Math.asin((startX - endX) / distance);
            addAngle = Math.PI * 0.25;
        }
        Line head1 = new Line(
                endX,
                endY,
                endX + 10 * Math.sin(lineAngle + addAngle),
                endY - 10 * Math.cos(lineAngle + addAngle)
        );
        Line head2 = new Line(
                endX,
                endY,
                endX + 10 * Math.sin(lineAngle - addAngle),
                endY - 10 * Math.cos(lineAngle - addAngle)
        );
        return new Group(line, head1, head2);
    }
}
