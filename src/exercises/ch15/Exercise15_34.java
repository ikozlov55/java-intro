package exercises.ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Stream;

public class Exercise15_34 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Lattice lattice = new Lattice(20, 16, 16);
        root.setCenter(lattice);
        BorderPane.setMargin(root, new Insets(5));
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> lattice.drawPath());
        root.setBottom(startButton);
        BorderPane.setAlignment(startButton, Pos.BOTTOM_CENTER);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(20 * 16 + 14);
        stage.setHeight(20 * 16 + 70);
        stage.setResizable(false);
        stage.show();
    }
}

class Lattice extends Pane {
    private final double n;
    private final int rows;
    private final int cols;

    private boolean[][] points;

    public Lattice(double n, int rows, int cols) {
        this.n = n;
        this.rows = rows;
        this.cols = cols;
        this.points = new boolean[rows + 1][cols + 1];
        draw();
    }

    private void draw() {
        getChildren().clear();
        for (int i = 0; i <= rows; i++) {
            Line line = new Line(0, n * i, n * cols, n * i);
            line.setOpacity(0.2);
            getChildren().add(line);
        }
        for (int i = 0; i <= cols; i++) {
            Line line = new Line(n * i, 0, n * i, n * rows);
            line.setOpacity(0.2);
            getChildren().add(line);
        }
    }

    public void drawPath() {
        draw();
        int x = cols / 2;
        int y = rows / 2;
        points = new boolean[rows + 1][cols + 1];
        points[x][y] = true;
        while (!reachedBorder(x, y) && !inDeadEnd(x, y)) {
            List<int[]> options = Stream.of(
                    new int[]{x - 1, y},
                    new int[]{x + 1, y},
                    new int[]{x, y - 1},
                    new int[]{x, y + 1}
            ).filter(p -> !points[p[0]][p[1]]).toList();

            int nextIndex = (int) (Math.random() * options.size());
            int nextX = options.get(nextIndex)[0];
            int nextY = options.get(nextIndex)[1];
            Line line = new Line(x * n, y * n, nextX * n, nextY * n);
            getChildren().add(line);
            x = nextX;
            y = nextY;
            points[x][y] = true;
        }
    }

    private boolean reachedBorder(int x, int y) {
        return x == 0 || x == cols || y == 0 || y == rows;
    }

    private boolean inDeadEnd(int x, int y) {
        return points[x - 1][y] && points[x + 1][y] && points[x][y - 1] && points[x][y + 1];
    }
}