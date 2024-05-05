package exercises.ch18.ex26;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

public class Exercise18_26 extends Application {
    private final Label label = new Label("");
    private final Button findBTN = new Button("Find Path");
    private final Button clearBTN = new Button("Clear Path");
    private final MazePane maze = new MazePane();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        clearBTN.setOnAction(e -> {
            maze.clear();
            label.setText("");
        });
        findBTN.setOnAction(e -> {
            String text = maze.canBeSolved() ? "path found" : "path cannot be found";
            label.setText(text);
        });
        stage.setScene(new Scene(makeRootPane()));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane pane = new BorderPane();
        pane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);

        HBox controls = new HBox(10);
        controls.getChildren().addAll(findBTN, clearBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        pane.setBottom(controls);

        pane.setCenter(maze);

        return pane;
    }
}

class MazePane extends GridPane {
    private final int side = 8;
    private final MazeCell[][] cells;

    public MazePane() {
        cells = new MazeCell[side][side];
        clear();
    }

    public void clear() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                MazeCell cell = new MazeCell();
                cell.setOnMouseClicked(this::handleCellClick);
                cells[i][j] = cell;
                add(cell, j, i);
            }
        }
    }

    public boolean canBeSolved() {
        return canBeSolvedHelper(0, 0);
    }

    public boolean canBeSolvedHelper(int x, int y) {
        if (cells[y][x].getState() == MazeCellState.MARKED) {
            return false;
        }
        cells[y][x].setState(MazeCellState.TRAVERSED);
        if (x == side - 1 && y == side - 1) {
            return true;
        }
        for (int[] point : getNextMoves(x, y)) {
            if (canBeSolvedHelper(point[0], point[1])) {
                return true;
            }
        }
        cells[y][x].setState(MazeCellState.CLEAR);
        return false;
    }

    private int[][] getNextMoves(int x, int y) {
        int[][] routes = {{x + 1, y}, {x, y + 1}, {x - 1, y}, {x, y - 1}};
        return Arrays.stream(routes).filter(point -> {
            int x1 = point[0];
            int y1 = point[1];
            return (x1 >= 0 && x1 < side &&
                    y1 >= 0 && y1 < side &&
                    cells[y1][x1].getState() != MazeCellState.MARKED &&
                    cells[y1][x1].getState() != MazeCellState.TRAVERSED &&
                    !hasSquarePath(x1, y1)
            );
        }).toArray(int[][]::new);
    }

    private boolean hasSquarePath(int x, int y) {
        int[][][] squarePoints = {
                {{x, y - 1}, {x - 1, y - 1}, {x - 1, y}},
                {{x, y + 1}, {x - 1, y + 1}, {x - 1, y}},
                {{x, y + 1}, {x + 1, y + 1}, {x + 1, y}},
                {{x + 1, y}, {x + 1, y - 1}, {x, y - 1}},
        };
        for (int[][] points : squarePoints) {
            boolean isSquarePath = Arrays.stream(points).
                    allMatch(point -> {
                        int x1 = point[0];
                        int y1 = point[1];
                        if (x1 < 0 || x1 >= side || y1 < 0 || y1 >= side) {
                            return false;
                        }
                        return cells[y1][x1].getState() == MazeCellState.TRAVERSED;
                    });
            if (isSquarePath) {
                return true;
            }
        }
        return false;
    }

    private void handleCellClick(MouseEvent e) {
        MazeCell cell = (MazeCell) e.getSource();
        switch (cell.getState()) {
            case CLEAR -> cell.setState(MazeCellState.MARKED);
            case MARKED -> cell.setState(MazeCellState.CLEAR);
        }
    }
}

class MazeCell extends Pane {
    private MazeCellState state;

    public MazeCell() {
        state = MazeCellState.CLEAR;
        paint();
    }

    private void paint() {
        getChildren().clear();
        double side = 50;
        setPrefSize(side, side);
        Rectangle border = new Rectangle(side, side, Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        getChildren().add(border);
        switch (state) {
            case CLEAR -> {
                setStyle("-fx-background-color: white");
            }
            case MARKED -> {
                Line line1 = new Line(0, 0, side, side);
                line1.setFill(Color.BLACK);
                Line line2 = new Line(side, 0, 0, side);
                line2.setFill(Color.BLACK);
                getChildren().addAll(line1, line2);
            }
            case TRAVERSED -> {
                setStyle("-fx-background-color: red");
            }
        }
    }

    public MazeCellState getState() {
        return state;
    }

    public void setState(MazeCellState state) {
        this.state = state;
        paint();
    }
}

enum MazeCellState {
    CLEAR, MARKED, TRAVERSED
}