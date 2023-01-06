package exercises.ch7;

import exercises.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Chapter7 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Assign grades) Write a program that reads student scores, gets the best score, and
        then assigns grades based on the following scheme:
        Grade is A if score is >= best -10;
        Grade is B if score is >= best -20;
        Grade is C if score is >= best -30;
        Grade is D if score is >= best -40;
        Grade is F otherwise.
        The program prompts the user to enter the total number of students, then prompts
        the user to enter all of the scores, and concludes by displaying the grades. Here is
        a sample run:
            Enter the number of students: 4
            Enter 4 scores: 40 55 70 58
            Student 0 score is 40 and grade is C
            Student 1 score is 55 and grade is B
            Student 2 score is 70 and grade is A
            Student 3 score is 58 and grade is B
     */
    public static void ch7_1() {
        System.out.print("Enter the number of students: ");
        int students = scanner.nextInt();
        System.out.print("Enter 4 scores: ");
        int[] scores = new int[students];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scanner.nextInt();
        }

        int bestScore = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > bestScore) {
                bestScore = scores[i];
            }
        }

        char[] grades = new char[students];
        for (int i = 0; i < grades.length; i++) {
            char grade;
            if (scores[i] >= bestScore - 10) {
                grade = 'A';
            } else if (scores[i] >= bestScore - 20) {
                grade = 'B';
            } else if (scores[i] >= bestScore - 30) {
                grade = 'C';
            } else if (scores[i] >= bestScore - 40) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            grades[i] = grade;
        }
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("Student %d score is %d and grade is %c\n", i, scores[i], grades[i]);
        }
    }

    /*
        (Reverse the numbers entered) Write a program that reads 10 integers then dis-
        plays them in the reverse of the order in which they were read.
     */
    public static void ch7_2() {
        final int NUMBER_OF_INTEGERS = 10;
        int[] arr = new int[NUMBER_OF_INTEGERS];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
    }

    /*
        (Count occurrence of numbers) Write a program that reads the integers between
        1 and 100 and counts the occurrences of each. Assume the input ends with 0. Here
        is a sample run of the program: Note that if a number occurs more than one time, the
        plural word “times” is used in the output. Numbers are displayed in increasing order.
            Enter the integers between 1 and 100: 2 5 6 5 4 3 23 43 2 0
            2 occurs 2 times
            3 occurs 1 time
            4 occurs 1 time
            5 occurs 2 times
            6 occurs 1 time
            23 occurs 1 time
            43 occurs 1 time
     */
    public static void ch7_3() {
        int[] arr = {};
        System.out.print("Enter the integers between 1 and 100: ");
        int next = scanner.nextInt();
        while (next != 0) {
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr[arr.length - 1] = next;
            next = scanner.nextInt();
        }
        Arrays.sort(arr);
        int[] occurrences = new int[100];
        for (int i : arr) {
            occurrences[i]++;
        }
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 0) continue;
            String plural = occurrences[i] == 1 ? "time" : "times";
            System.out.printf("%d occurs %d %s\n", i, occurrences[i], plural);
        }
    }

    /*
        (Analyze scores) Write a program that reads an unspecified number of scores and
        determines how many scores are above or equal to the average, and how many
        scores are below the average. Enter a negative number to signify the end of the
        input. Assume the maximum number of scores is 100.
     */
    public static void ch7_4() {
        int[] scores = {};
        System.out.print("Enter scores: ");
        int next = scanner.nextInt();
        while (next >= 0) {
            scores = Arrays.copyOf(scores, scores.length + 1);
            scores[scores.length - 1] = next;
            next = scanner.nextInt();
        }
        int sum = Arrays.stream(scores).reduce(0, Integer::sum);
        int average = sum / scores.length;
        long aboveAverage = Arrays.stream(scores).filter(x -> x >= average).count();
        long belowAverage = scores.length - aboveAverage;
        System.out.println("Average score is " + average);
        System.out.println(aboveAverage + " scores are above average");
        System.out.println(belowAverage + " scores are below average");
    }

    /*
        (Print distinct numbers) Write a program that reads in 10 numbers and displays the
        number of distinct numbers and the distinct numbers in their input order and sepa-
        rated by exactly one space (i.e., if a number appears multiple times, it is displayed
        only once). (Hint: Read a number and store it to an array if it is new. If the number is
        already in the array, ignore it.) After the input, the array contains the distinct numbers.
        Here is the sample run of the program:
            Enter 10 numbers: 1 2 3 2 1 6 3 4 5 2
            The number of distinct numbers is 6
            The distinct numbers are: 1 2 3 6 4 5
     */
    public static void ch7_5() {
        final int NUMBERS = 10;
        int[] numbers = {};
        System.out.printf("Enter %d numbers: ", NUMBERS);
        for (int i = 0; i < NUMBERS; i++) {
            int next = scanner.nextInt();
            if (!ArrayUtils.contains(numbers, next)) {
                numbers = Arrays.copyOf(numbers, numbers.length + 1);
                numbers[numbers.length - 1] = next;
            }
        }
        System.out.println("The number of distinct numbers is " + numbers.length);
        System.out.print("The distinct numbers are:");
        Arrays.stream(numbers).forEach(x -> System.out.print(" " + x));
    }

    /*
        (Revise Listing 5.15, PrimeNumber.java) Listing 5.15 determines whether a num-
        ber n is prime by checking whether 2, 3, 4, 5, 6, . . . , n/2 is a divisor. If a divisor
        is found, n is not prime. A more efficient approach is to check whether any of the
        prime numbers less than or equal to sqrt(n) can divide n evenly. If not, n is prime.
        Rewrite Listing 5.15 to display the first 50 prime numbers using this approach.
        You need to use an array to store the prime numbers, and later use them to check
        whether they are possible divisors for n.
            The first 50 prime numbers are

            2 3 5 7 11 13 17 19 23 29
            31 37 41 43 47 53 59 61 67 71
            73 79 83 89 97 101 103 107 109 113
            127 131 137 139 149 151 157 163 167 173
            179 181 191 193 197 199 211 223 227 229
     */
    public static void ch7_6() {
        final int NUMBER_OF_PRIMES = 50; // Number of primes to display
        final int NUMBER_OF_PRIMES_PER_LINE = 10; // Display 10 per line
        int count = 0; // Count the number of prime numbers
        int number = 2; // A number to be tested for primeness
        int[] primes = {};

        System.out.println("The first 50 prime numbers are \n");

        // Repeatedly find prime numbers
        while (count < NUMBER_OF_PRIMES) {
            // Assume the number is prime
            boolean isPrime = true; // Is the current number prime?

            // Test whether number is prime
            for (int prime : primes) {
                if (prime > Math.sqrt(number)) break;
                if (number % prime == 0) {
                    isPrime = false;
                    break;
                }
            }

            // Display the prime number and increase the count
            if (isPrime) {
                count++; // Increase the count
                primes = Arrays.copyOf(primes, count);
                primes[count - 1] = number;

                if (count % NUMBER_OF_PRIMES_PER_LINE == 0) {
                    // Display the number and advance to the new line
                    System.out.println(number);
                } else
                    System.out.print(number + " ");
            }

            // Check if the next number is prime
            number++;
        }
    }

    /*
        (Count single digits) Write a program that generates 100 random integers between
        0 and 9 and displays the count for each number. (Hint: Use an array of 10 integers,
        say counts, to store the counts for the number of 0s, 1s, . . . , 9s.
     */
    public static void ch7_7() {
        final int NUMBERS = 100;
        int[] numbers = new int[NUMBERS];
        for (int i = 0; i < NUMBERS; i++) {
            numbers[i] = (int) (Math.random() * 10);
        }
        int[] counts = new int[10];
        for (int number : numbers) {
            counts[number]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.printf("Number %d occurs %d %s\n", i, counts[i], counts[i] == 1 ? "time" : "times");
        }
    }

    /*
        (Average an array) Write two overloaded methods that return the average of an
        array with the following headers:
        public static double average(int[] array)
        public static double average(double[] array)
        Write a test program that prompts the user to enter 10 integers, invokes the first
        method, then displays the average value; prompts the user to enter 10 double
        values, invokes the second method, then displays the average value.
     */
    public static void ch7_8() {
        final int NUMBERS = 10;
        int[] intArray = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d integers: ", NUMBERS));
        System.out.printf("The average value is %.2f\n", average(intArray));
        double[] doubleArray = ArrayUtils.inputDoubleArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        System.out.printf("The average value is %.2f\n", average(doubleArray));
    }

    public static double average(int[] array) {
        double total = Arrays.stream(array).reduce(0, Integer::sum);
        return total / array.length;
    }

    public static double average(double[] array) {
        double total = Arrays.stream(array).reduce(0, Double::sum);
        return total / array.length;
    }

    /*
        (Find the smallest element) Write a method that finds the smallest element in an
        array of double values using the following header:
        public static double min(double[] array)
        Write a test program that prompts the user to enter 10 numbers, invokes this
        method to return the minimum value, and displays the minimum value. Here is a
        sample run of the program:
        Enter 10 numbers: 1.9 2.5 3.7 2 1.5 6 3 4 5 2
        The minimum number is 1.5
     */
    public static void ch7_9() {
        final int NUMBERS = 10;
        double[] array = ArrayUtils.inputDoubleArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        System.out.println("The minimum number is " + min(array));
    }

    public static double min(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /*
        (Find the index of the smallest element) Write a method that returns the index of
        the smallest element in an array of integers. If the number of such elements is
        greater than 1, return the smallest index. Use the following header:
        public static int indexOfSmallestElement(double[] array)
        Write a test program that prompts the user to enter 10 numbers, invokes this
        method to return the index of the smallest element, and displays the index.
     */
    public static void ch7_10() {
        final int NUMBERS = 10;
        double[] array = ArrayUtils.inputDoubleArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        System.out.println("Index of the smallest element is " + indexOfSmallestElement(array));
    }

    public static int indexOfSmallestElement(double[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /*
        (Statistics: compute deviation) Programming Exercise 5.45 computes the standard
        deviation of numbers. This exercise uses a different but equivalent formula to
        compute the standard deviation of n numbers.
        mean = sigma(i=1, n)xi/n = (x1+x2+...+xn)/n
        deviation = sqrt(sigma(i=1, n)(xi-mean^2)/(n-1))
        To compute the standard deviation with this formula, you have to store the indi-
        vidual numbers using an array, so they can be used after the mean is obtained.
        Your program should contain the following methods:
        Compute the deviation of double values:
        public static double deviation(double[] x)
        Compute the mean of an array of double values:
        public static double mean(double[] x)
        Write a test program that prompts the user to enter 10 numbers and displays the
        mean and standard deviation, as presented in the following sample run:
            Enter 10 numbers: 1.9 2.5 3.7 2 1 6 3 4 5 2
            The mean is 3.11
            The standard deviation is 1.55738
     */
    public static void ch7_11() {
        final int NUMBERS = 10;
        double[] array = ArrayUtils.inputDoubleArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        double mean = mean(array);
        double deviation = deviation(array);
        System.out.printf("The mean is %.2f\n", mean);
        System.out.printf("The standard deviation is %.5f\n", deviation);
    }

    public static double deviation(double[] x) {
        double mean = mean(x);
        double sigma = 0;
        for (double number : x) {
            sigma += Math.pow(number - mean, 2);
        }
        return Math.sqrt(sigma / (x.length - 1));
    }

    public static double mean(double[] x) {
        double total = Arrays.stream(x).reduce(0, Double::sum);
        return total / x.length;
    }


    /*
        (Reverse an array) The reverse method in Section 7.7 reverses an array by
        copying it to a new array. Rewrite the method that reverses the array passed in
        the argument and returns this array. Write a test program that prompts the user to
        enter 10 numbers, invokes the method to reverse the numbers, and displays the
        numbers.
     */
    public static void ch7_12() {
        final int NUMBERS = 10;
        int[] array = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d integers: ", NUMBERS));
        System.out.println("The reversed array is " + Arrays.toString(reverse(array)));
    }

    public static int[] reverse(int[] list) {
        for (int i = 0; i < (list.length - 1) / 2; i++) {
            int temp = list[i];
            list[i] = list[list.length - 1 - i];
            list[list.length - 1 - i] = temp;
        }

        return list;
    }

    /*

     */
    public static void ch7_13() {
    }

    /*

     */
    public static void ch7_14() {
    }

    /*

     */
    public static void ch7_15() {
    }

    /*

     */
    public static void ch7_16() {
    }

    /*

     */
    public static void ch7_17() {
    }

    /*

     */
    public static void ch7_18() {
    }

    /*

     */
    public static void ch7_19() {
    }

    /*

     */
    public static void ch7_20() {
    }

    /*

     */
    public static void ch7_21() {
    }

    /*

     */
    public static void ch7_22() {
    }

    /*

     */
    public static void ch7_23() {
    }

    /*

     */
    public static void ch7_24() {
    }

    /*

     */
    public static void ch7_25() {
    }

    /*

     */
    public static void ch7_26() {
    }

    /*

     */
    public static void ch7_27() {
    }

    /*

     */
    public static void ch7_28() {
    }

    /*

     */
    public static void ch7_29() {
    }

    /*

     */
    public static void ch7_30() {
    }

    /*

     */
    public static void ch7_31() {
    }

    /*

     */
    public static void ch7_32() {
    }

    /*

     */
    public static void ch7_33() {
    }

    /*

     */
    public static void ch7_34() {
    }

    /*

     */
    public static void ch7_35() {
    }

    /*

     */
    public static void ch7_36() {
    }

    /*

     */
    public static void ch7_37() {
    }

}
