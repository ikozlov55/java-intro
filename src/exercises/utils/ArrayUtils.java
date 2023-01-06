package exercises.utils;

import java.util.Locale;
import java.util.Scanner;

public class ArrayUtils {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static boolean contains(int[] arr, int value) {
        for (int i : arr) {
            if (i == value) return true;
        }
        return false;
    }

    public static int[] inputIntArray(int length, String prompt) {
        int[] result = new int[length];
        System.out.print(prompt);
        for (int i = 0; i < length; i++) {
            result[i] = scanner.nextInt();
        }
        return result;
    }

    public static double[] inputDoubleArray(int length, String prompt) {
        double[] result = new double[length];
        System.out.print(prompt);
        for (int i = 0; i < length; i++) {
            result[i] = scanner.nextDouble();
        }
        return result;
    }
}
