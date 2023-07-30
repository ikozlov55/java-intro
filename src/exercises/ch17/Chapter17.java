package exercises.ch17;

import java.io.*;
import java.util.ArrayList;

public class Chapter17 {
    /*
        (Create a text file) Write a program to create a file named Exercise17_01.txt if
        it does not exist. Append new data to it if it already exists. Write 100 integers
        created randomly into the file using text I/O. Integers are separated by a space.
     */
    public static void ch17_1() {
        File file = new File("src/exercises/ch17/data/Exercise17_01.txt");
        try (
                FileOutputStream fos = new FileOutputStream(file, true);
                PrintWriter writer = new PrintWriter(fos)
        ) {
            for (int i = 0; i < 100; i++) {
                int value = (int) (Math.random() * 100);
                writer.print(value);
                writer.print(' ');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Create a binary data file) Write a program to create a file named Exercise17_02
        .dat if it does not exist. Append new data to it if it already exists. Write 100 inte-
        gers created randomly into the file using binary I/O.
     */
    public static void ch17_2() {
        File file = new File("src/exercises/ch17/data/Exercise17_02.dat");
        try (
                FileOutputStream fos = new FileOutputStream(file, true);
                DataOutputStream stream = new DataOutputStream(fos)
        ) {
            for (int i = 0; i < 100; i++) {
                int value = (int) (Math.random() * 100);
                stream.writeInt(value);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Sum all the integers in a binary data file) Suppose a binary data file named
        Exercise17_02.dat has been created from Programming Exercise 17.2 and its data
        are created using writeInt(int) in DataOutputStream. The file contains an
        unspecified number of integers. Write a program to find the sum of the integers.
     */
    public static void ch17_3() {
        File file = new File("src/exercises/ch17/data/Exercise17_02.dat");
        ArrayList<Integer> list = new ArrayList<>();
        try (
                FileInputStream fos = new FileInputStream(file);
                DataInputStream stream = new DataInputStream(fos)
        ) {
            while (stream.available() > 0) {
                list.add(stream.readInt());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /*

     */
    public static void ch17_4() {

    }

    /*

     */
    public static void ch17_5() {

    }

    /*

     */
    public static void ch17_6() {

    }

    /*

     */
    public static void ch17_7() {

    }

    /*

     */
    public static void ch17_8() {

    }

    /*

     */
    public static void ch17_9() {

    }

    /*

     */
    public static void ch17_10() {

    }

    /*

     */
    public static void ch17_11() {

    }

    /*

     */
    public static void ch17_12() {

    }

    /*

     */
    public static void ch17_13() {

    }

    /*

     */
    public static void ch17_14() {

    }

    /*

     */
    public static void ch17_15() {

    }

    /*

     */
    public static void ch17_16() {

    }

    /*

     */
    public static void ch17_17() {

    }

    /*

     */
    public static void ch17_18() {

    }

    /*

     */
    public static void ch17_19() {

    }

    /*

     */
    public static void ch17_20() {

    }

    /*

     */
    public static void ch17_21() {

    }
}
