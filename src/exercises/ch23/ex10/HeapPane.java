package exercises.ch23.ex10;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class HeapPane<E> extends Pane {
    private final Heap<E> heap;

    public HeapPane() {
        this.heap = new Heap<>();
        draw();
    }

    public HeapPane(E[] list) {
        this.heap = new Heap<>(list);
        draw();
    }

    public void add(E item) {
        heap.add(item);
        draw();
    }

    public void remove() {
        heap.remove();
        draw();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        draw();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        draw();
    }

    private void draw() {
        getChildren().clear();
        if (heap.isEmpty()) {
            Text text = new Text("Heap is Empty");
            text.xProperty().bind(widthProperty().divide(2).subtract(40));
            text.yProperty().bind(heightProperty().divide(2));
            getChildren().add(text);
        }
        double x = getWidth() / 2;
        double y = 30;
        addNode(x, y, 0);
    }

    private void addNode(double x, double y, int i) {
        if (i > heap.getSize() - 1) return;
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;
        double xStep = getWidth() / (2 * (i + 3));
        double nextY = y + 50;
        if (leftChildIndex < heap.getSize()) {
            getChildren().add(new Line(x, y, x - xStep, nextY));
            addNode(x - xStep, nextY, leftChildIndex);
        }
        if (rightChildIndex < heap.getSize()) {
            getChildren().add(new Line(x, y, x + xStep, nextY));
            addNode(x + xStep, nextY, rightChildIndex);
        }
        drawNode(x, y, heap.peek(i));
    }

    private void drawNode(double x, double y, E value) {
        double radius = 16;
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text text = new Text(value.toString());
        double length = value.toString().length() * 7;
        text.xProperty().bind(circle.centerXProperty().subtract(length / 2));
        text.yProperty().bind(circle.centerYProperty().add(radius / 4));
        getChildren().addAll(circle, text);
    }

}
