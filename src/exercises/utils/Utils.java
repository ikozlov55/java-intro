package exercises.utils;

public class Utils {
    public static double testTime(Runnable action) {
        long startTime = System.currentTimeMillis();
        action.run();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }
}
