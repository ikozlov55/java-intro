package exercises.ch17;

import exercises.ch17.ex11.Exercise17_11;
import exercises.ch17.ex13.Exercise17_13;
import exercises.ch17.ex6.Loan;
import exercises.ch17.ex9.Exercise17_09;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        (Convert a text file into UTF) Write a program that reads lines of characters from
        a text file and writes each line as a UTF string into a binary file. Display the
        sizes of the text file and the binary file. Use the following command to run the
        program:
        java Exercise17_04 Welcome.java Welcome.utf

        src/exercises/ch17/Chapter17.java src/exercises/ch17/data/Exercise17_4.utf
     */
    public static void ch17_4(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        File input = new File(args[0]);
        File output = new File(args[1]);
        try (
                Scanner scanner = new Scanner(input);
                FileOutputStream out = new FileOutputStream(output);
                DataOutputStream stream = new DataOutputStream(out)
        ) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                stream.writeUTF(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("%s size is: %d\n", input.getName(), input.length());
        System.out.printf("%s size is: %d\n", output.getName(), output.length());
    }

    /*
        (Store objects and arrays in a file) Write a program that stores an array of the five
        int values 1, 2, 3, 4, and 5, a Date object for the current time, and the double
        value 5.5 into the file named Exercise17_05.dat. In the same program, write the
        code to read and display the data.
     */
    public static void ch17_5() {
        File file = new File("src/exercises/ch17/data/Exercise17_05.dat");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(new int[]{1, 2, 3, 4, 5});
            stream.writeObject(new Date());
            stream.writeDouble(5.5);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            int[] array = (int[]) stream.readObject();
            Date date = (Date) stream.readObject();
            double value = stream.readDouble();
            System.out.println(Arrays.toString(array));
            System.out.println(date);
            System.out.println(value);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Store Loan objects) The Loan class in Listing 10.2 does not implement
        Serializable. Rewrite the Loan class to implement Serializable. Write
        a program that creates five Loan objects and stores them in a file named
        Exercise17_06.dat.
     */
    public static void ch17_6() {
        File file = new File("src/exercises/ch17/data/Exercise17_06.dat");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 1; i <= 5; i++) {
                Loan loan = new Loan(5, i, 100_000);
                stream.writeObject(loan);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    /*
        (Restore objects from a file) Suppose a file named Exercise17_06.dat has
        been created using the ObjectOutputStream from the preceding program-
        ming exercises. The file contains Loan objects. The Loan class in Listing 10.2
        does not implement Serializable. Rewrite the Loan class to implement
        Serializable. Write a program that reads the Loan objects from the file and
        displays the total loan amount. Suppose that you don’t know how many Loan
        objects are there in the file, use EOFException to end the loop.
     */
    public static void ch17_7() {
        File file = new File("src/exercises/ch17/data/Exercise17_06.dat");
        ArrayList<Loan> loans = new ArrayList<>();
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Loan loan = (Loan) stream.readObject();
                loans.add(loan);
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        double totalLoan = loans.stream().map(Loan::getLoanAmount).reduce(0.0, Double::sum);
        System.out.printf("Total loan amount is %.2f", totalLoan);
    }

    /*
        (Update count) Suppose that you wish to track how many times a program has
        been executed. You can store an int to count the file. Increase the count by 1
        each time this program is executed. Let the program be Exercise17_08.txt and
        store the count in Exercise17_08.dat.
     */
    public static void ch17_8() {
        File counterFile = new File("src/exercises/ch17/data/Exercise17_08.dat");
        int count;
        try (DataInputStream stream = new DataInputStream(new FileInputStream(counterFile))) {
            count = stream.readInt();
        } catch (FileNotFoundException e) {
            count = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
        count++;
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(counterFile))) {
            stream.writeInt(count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Address book) Write a program that stores, retrieves, adds, and updates addresses as
        shown in Figure 17.20. Use a fixed-length string for storing each attribute in the address.
        Use random-access file for reading and writing an address. Assume the sizes of the
        name, street, city, state, and zip are 32, 32, 20, 2, and 5 bytes, respectively.
     */
    public static void ch17_9() {
        Exercise17_09.run();
    }

    /*
        (Split files) Suppose you want to back up a huge file (e.g., a 10-GB AVI file) to a
        CD-R. You can achieve it by splitting the file into smaller pieces and backing up
        these pieces separately. Write a utility program that splits a large file into smaller
        ones using the following command:
        java Exercise17_10 SourceFile numberOfPieces
        The command creates the files SourceFile.1, SourceFile.2, . . . , SourceFile.n,
        where n is numberOfPieces and the output files are about the same size
     */
    public static void ch17_10(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input");
            System.exit(1);
        }
        File file = new File(args[0]);
        String fileName = file.getName().split("\\.")[0];
        try (FileInputStream input = new FileInputStream(file)) {
            int n = Integer.parseInt(args[1]);
            int chunkSize = input.available() / n;
            for (int i = 1; i <= n; i++) {
                String filePath = String.format("src/exercises/ch17/data/%s.%d", fileName, i);
                byte[] chunk = new byte[i == n ? input.available() : chunkSize];
                input.read(chunk);
                try (FileOutputStream output = new FileOutputStream(filePath);) {
                    output.write(chunk);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Split files GUI) Rewrite Exercise 17.10 with a GUI, as shown in Figure 17.21a.
     */
    public static void ch17_11() {
        Exercise17_11.run();
    }

    /*
        (Combine files) Write a utility program that combines the files together into a
        new file using the following command:
        java Exercise17_12 SourceFile1 . . . SourceFilen TargetFile
        The command combines SourceFile1, . . . , and SourceFilen into TargetFile.
     */
    public static void ch17_12(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid input");
            System.exit(1);
        }
        File targetFile = new File(args[args.length - 1]);
        File[] sourceFiles = IntStream.range(0, args.length - 1)
                .mapToObj(i -> new File(args[i]))
                .toArray(File[]::new);
        try (FileOutputStream output = new FileOutputStream(targetFile)) {
            for (File sourceFile : sourceFiles) {
                FileInputStream input = new FileInputStream(sourceFile);
                byte[] chunk = input.readAllBytes();
                output.write(chunk);
                input.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Combine files GUI) Rewrite Exercise 17.12 with a GUI, as shown in Figure 17.21b.
     */
    public static void ch17_13() {
        Exercise17_13.run();
    }

    /*
        (Encrypt files) Encode the file by adding 5 to every byte in the file. Write a pro-
        gram that prompts the user to enter an input file name and an output file name and
        saves the encrypted version of the input file to the output file.
     */
    public static void ch17_14() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter input file name:");
            File inputFile = new File(scanner.nextLine());
            System.out.println("Enter output file name:");
            File outputFile = new File(scanner.nextLine());
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            while (inputStream.available() > 0) {
                int b = inputStream.read();
                b += 5;
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Decrypt files) Suppose a file is encrypted using the scheme in Programming
        Exercise 17.14. Write a program to decode an encrypted file. Your program
        should prompt the user to enter an input file name for the encrypted file and an
        output file name for the unencrypted version of the input file.
     */
    public static void ch17_15() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter input file name:");
            File inputFile = new File(scanner.nextLine());
            System.out.println("Enter output file name:");
            File outputFile = new File(scanner.nextLine());
            FileInputStream inputStream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            while (inputStream.available() > 0) {
                int b = inputStream.read();
                b -= 5;
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Frequency of characters) Write a program that prompts the user to enter the
        name of an ASCII text file and displays the frequency of the characters in the file.

        src/exercises/ch17/Chapter17.java
     */
    public static void ch17_16() {
        int[] frequencies = new int[128];
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter input file name:");
            File inputFile = new File(scanner.nextLine());
            FileInputStream inputStream = new FileInputStream(inputFile);
            while (inputStream.available() > 0) {
                char c = (char) inputStream.read();
                if (c > 127) continue;
                frequencies[c]++;
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < frequencies.length; i++) {
            System.out.printf("%c: %d\n", (char) i, frequencies[i]);
        }
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
