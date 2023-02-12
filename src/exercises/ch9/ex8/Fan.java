package exercises.ch9.ex8;


public class Fan {
    public static int SLOW = 1;
    public static int MEDIUM = 2;
    public static int FAST = 3;
    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5;
    private String color = "blue";

    public Fan() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String result = on ? String.format("speed: %d\n", speed) : "fan is off\n";
        result += String.format("color: %s\nradius: %.2f\n", color, radius);
        return result;
    }
}
