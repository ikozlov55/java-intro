package exercises.ch7;

import exercises.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        System.out.printf("Enter %d scores: ", students);
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
        (Random number chooser) Write the following method that returns a random
        number between start and end, excluding the numbers.
        public static int getRandom(int start, int end, int... numbers)
        For example, invoking getRandom(1,100,4,8,95,93) returns a random num-
        ber between 1 and 100 excluding 4,8,95,and 93. Write a test program that
        invokes getRandom(1,100,4,8,95,93) 45 times and displays the resulting
        numbers 15 per line using the format %4d.
     */
    public static void ch7_13() {
        final int NUMBERS = 45;
        final int NUMBERS_PER_LINE = 15;
        for (int i = 1; i <= NUMBERS; i++) {
            int random = getRandom(1, 100, 4, 8, 95, 93);
            System.out.printf("%4d", random);
            if (i % NUMBERS_PER_LINE == 0) {
                System.out.println();
            }
        }
    }

    public static int getRandom(int start, int end, int... numbers) {
        int result;
        do {
            result = (int) (Math.random() * (end - start - 1)) + start + 1;
        } while (ArrayUtils.contains(numbers, result));
        return result;
    }

    /*
        (Compute gcd) Write a method that returns the gcd of an unspecified number of
        integers. The method header is specified as follows:
        public static int gcd(int... numbers)
        Write a test program that prompts the user to enter five numbers, invokes the
        method to find the gcd of these numbers, and displays the gcd.
     */
    public static void ch7_14() {
        final int NUMBERS = 5;
        int[] numbers = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        System.out.println(gcd(numbers));
    }

    public static int gcd(int... numbers) {
        int min = ArrayUtils.min(numbers);
        while (min > 0) {
            boolean isGcd = true;
            for (int number : numbers) {
                isGcd = number % min == 0;
                if (!isGcd) break;
            }
            if (isGcd) break;
            min--;
        }
        return min;
    }

    /*
        (Eliminate duplicates) Write a method that returns a new array by eliminating the
        duplicate values in the array using the following method header:
        public static int[] eliminateDuplicates(int[] list)
        Write a test program that reads in 10 integers, invokes the method, and displays
        the distinct numbers separated by exactly one space. Here is a sample run of the
        program:
        Enter 10 numbers: 1 2 3 2 1 6 3 4 5 2
        The distinct numbers are: 1 2 3 6 4 5
     */
    public static void ch7_15() {
        final int NUMBERS = 10;
        int[] numbers = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        System.out.print("The distinct numbers are: ");
        Arrays.stream(eliminateDuplicates(numbers)).forEach(x -> System.out.print(x + " "));
    }

    public static int[] eliminateDuplicates(int[] list) {
        int[] result = {};
        for (int number : list) {
            if (!ArrayUtils.contains(result, number)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = number;
            }
        }
        return result;
    }

    /*
        (Execution time) Write a program that randomly generates an array of 100,000
        integers and a key. Estimate the execution time of invoking the linearSearch
        method in Listing 7.6. Sort the array and estimate the execution time of invoking
        the binarySearch method in Listing 7.7. You can use the following code tem-
        plate to obtain the execution time:
        long startTime = System.nanoTime();
        perform the task;
        long endTime = System.nanoTime();
        long executionTime = endTime − startTime;
     */
    public static void ch7_16() {
        final int NUMBERS = 100_000;
        int[] numbers = new int[NUMBERS];
        for (int i = 0; i < NUMBERS; i++) {
            numbers[i] = (int) (Math.random() * 101) + 1;
        }
        int key = (int) (Math.random() * 101) + 1;

        System.out.println("Linear search execution time: " + run(() -> {
            System.out.println(linearSearch(numbers, key));
            return null;
        }));

        Arrays.sort(numbers);

        System.out.println("Binary search execution time: " + run(() -> {
            System.out.println(binarySearch(numbers, key));
            return null;
        }));
    }

    public static long run(Supplier<Long> func) {
        long startTime = System.nanoTime();
        func.get();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static int linearSearch(int[] list, int key) {
        for (int i = 0; i < list.length; i++) {
            if (key == list[i])
                return i;
        }
        return -1;
    }

    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list[mid])
                high = mid - 1;
            else if (key == list[mid])
                return mid;
            else
                low = mid + 1;
        }

        return -low - 1;
    }

    /*
        (Sort students) Write a program that prompts the user to enter the number of stu-
        dents, the students’ names, and their scores and prints student names in decreasing
        order of their scores. Assume the name is a string without spaces, use the Scan­
        ner’s next() method to read a name.
     */
    public static void ch7_17() {
        System.out.print("Enter a number of students: ");
        int numberOfStudents = scanner.nextInt();
        String[] students = ArrayUtils.inputStringArray(numberOfStudents, "Enter student names: ");
        int[] scores = ArrayUtils.inputIntArray(numberOfStudents, "Enter student scores: ");
        for (int i = 0; i < numberOfStudents - 1; i++) {
            int currentMin = scores[i];
            int minIndex = i;
            for (int j = i + 1; j < numberOfStudents; j++) {
                if (scores[j] < currentMin) {
                    currentMin = scores[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                scores[minIndex] = scores[i];
                scores[i] = currentMin;
                String temp = students[i];
                students[i] = students[minIndex];
                students[minIndex] = temp;
            }
        }

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.printf("%s - %d\n", students[i], scores[i]);
        }
    }

    /*
        (Bubble sort) Write a sort method that uses the bubble-sort algorithm. The
        bubble-sort algorithm makes several passes through the array. On each pass, suc-
        cessive neighboring pairs are compared. If a pair is not in order, its values are
        swapped; otherwise, the values remain unchanged. The technique is called a bub-
        ble sort or sinking sort because the smaller values gradually “bubble” their way to
        the top, and the larger values “sink” to the bottom. Write a test program that reads
        in 10 double numbers, invokes the method, and displays the sorted numbers.
     */
    public static void ch7_18() {
        final int NUMBERS = 10;
        int[] array = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] += array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] -= array[j + 1];
                }
            }
        }
    }

    /*
        (Sorted?) Write the following method that returns true if the list is already sorted
        in nondecreasing order:
        public static boolean isSorted(int[] list)
        Write a test program that prompts the user to enter a list and displays whether the
        list is sorted or not. Here is a sample run. Note that the program first prompts the
        user to enter the size of the list.
            Enter the size of the list: 8
            Enter the contents of the list: 10 1 5 16 61 9 11 1
            The list has 8 integers 10 1 5 16 61 9 11 1
            The list is not sorted
            Enter the size of the list: 10
            Enter the contents of the list: 1 1 3 4 4 5 7 9 11 21
            The list has 10 integers 1 1 3 4 4 5 7 9 11 21
            The list is already sorted
     */
    public static void ch7_19() {
        System.out.print("Enter the size of the list: ");
        int size = scanner.nextInt();
        int[] list = ArrayUtils.inputIntArray(size, "Enter the contents of the list: ");
        System.out.printf("The list has %d integers", size);
        for (int i : list) {
            System.out.print(" " + i);
        }
        System.out.println();
        if (isSorted(list)) {
            System.out.println("The list is already sorted");
        } else {
            System.out.println("The list is not sorted");
        }
    }

    public static boolean isSorted(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i + 1] < list[i]) {
                return false;
            }
        }
        return true;
    }

    /*
        (Revise selection sort) In Listing 7.8, you used selection sort to sort an array. The
        selection-sort method repeatedly finds the smallest number in the current array
        and swaps it with the first. Rewrite this program by finding the largest number and
        swapping it with the last. Write a test program that reads in 10 double numbers,
        invokes the method, and displays the sorted numbers.
            Enter 10 numbers: 5.0 9.5 2.0 9.3 0 3.0 0.7 5.0 1.0 2.0
            [0.0, 0.7, 1.0, 2.0, 2.0, 3.0, 5.0, 5.0, 9.3, 9.5]
     */
    public static void ch7_20() {
        final int NUMBERS = 10;
        double[] array = ArrayUtils.inputDoubleArray(NUMBERS, String.format("Enter %d numbers: ", NUMBERS));
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void selectionSort(double[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            double currentMax = list[i];
            int currentMaxIndex = i;

            for (int j = 0; j < i; j++) {
                if (list[j] > currentMax) {
                    currentMax = list[j];
                    currentMaxIndex = j;
                }
            }

            if (currentMaxIndex != i) {
                list[currentMaxIndex] = list[i];
                list[i] = currentMax;
            }
        }
    }

    /*
        (Sum integers) Write a program that passes an unspecified number of integers from
        command line and displays their total.
     */
    public static void ch7_21(String... args) {
        int sum = Arrays.stream(args).map(Integer::parseInt).reduce(0, Integer::sum);
        System.out.println(sum);
    }

    /*
        (Find the number of uppercase letters in a string) Write a program that passes a string
        to the command line and displays the number of uppercase letters in the string.
     */
    public static void ch7_22(String... args) {
        int uppercaseLetters = 0;
        if (args.length == 0) {
            System.out.println(0);
            return;
        }
        for (char c : args[0].toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseLetters++;
            }
        }
        System.out.println(uppercaseLetters);
    }

    /*
        (Game: locker puzzle) A school has 100 lockers and 100 students. All lockers are
        closed on the first day of school. As the students enter, the first student, denoted
        as S1, opens every locker. Then the second student, S2, begins with the second
        locker, denoted as L2, and closes every other locker. Student S3 begins with the
        third locker and changes every third locker (closes it if it was open and opens it if
        it was closed). Student S4 begins with locker L4 and changes every fourth locker.
        Student S5 starts with L5 and changes every fifth locker, and so on, until student
        S100 changes L100.
        After all the students have passed through the building and changed the lockers,
        which lockers are open? Write a program to find your answer and display all open
        locker numbers separated by exactly one space.
        (Hint: Use an array of 100 Boolean elements, each of which indicates whether a
        locker is open (true) or closed (false). Initially, all lockers are closed.)
     */
    public static void ch7_23() {
        final int NUMBER_OF_STUDENTS = 100;
        boolean[] lockers = new boolean[NUMBER_OF_STUDENTS];
        for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
            for (int j = i; j < NUMBER_OF_STUDENTS; j += (i == 0 || i == 1) ? 1 : i + 1) {
                lockers[j] = !lockers[j];
            }
        }
        for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
            if (lockers[i]) {
                System.out.print(i + 1 + " ");
            }
        }
    }

    /*
        (Simulation: coupon collector’s problem) Coupon collector is a classic statistics
        problem with many practical applications. The problem is to repeatedly pick
        objects from a set of objects and find out how many picks are needed for all the
        objects to be picked at least once. A variation of the problem is to pick cards from
        a shuffled deck of 52 cards repeatedly, and find out how many picks are needed
        before you see one of each suit. Assume a picked card is placed back in the deck
        before picking another. Write a program to simulate the number of picks needed
        to get four cards from each suit and display the four cards picked (it is possible a
        card may be picked twice). Here is a sample run of the program:
            Queen of Spades
            5 of Clubs
            Queen of Hearts
            4 of Diamonds
            Number of picks: 12
     */
    public static void ch7_24() {
        final int CARDS = 4;
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] deck = new int[52];
        // Initialize cards
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i;
        }
        // Shuffle the cards
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
        int picks = 0;
        int[] currentPick = new int[CARDS];

        while (true) {
            for (int i = 0; i < CARDS; i++) {
                currentPick[i] = (int) (Math.random() * deck.length);
            }
            int[] currentRanks = Arrays.stream(currentPick).map(x -> x / 12).toArray();
            Arrays.sort(currentRanks);
            if (Arrays.equals(currentRanks, new int[]{0, 1, 2, 3})) {
                break;
            }
            picks++;
        }
        for (int card : currentPick) {
            System.out.printf("%s of %s\n", ranks[card % 13], suits[card / 13]);
        }
        System.out.println("Number of picks: " + picks);
    }

    /*
        (Algebra: solve quadratic equations) Write a method for solving a quadratic equa-
        tion using the following header:
        public static int solveQuadratic(double[] eqn, double[] roots)
        The coefficients of a quadratic equation ax^2 + bx + c = 0 are passed to the array
        eqn and the real roots are stored in roots. The method returns the number of real
        roots. See Programming Exercise 3.1 on how to solve a quadratic equation.
        Write a program that prompts the user to enter values for a, b, and c and displays
        the number of real roots and all real roots.
            Enter a, b, c: 1.0 3 1
            The equation has two roots −0.381966 and −2.61803
            Enter a, b, c: 1 2.0 1
            The equation has one root −1.0
            Enter a, b, c: 1 2 3
            The equation has no real roots
     */
    public static void ch7_25() {
        System.out.print("Enter a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double[] roots = new double[2];
        double[] eqn = {a, b, c};
        int numberOfRoots = solveQuadratic(eqn, roots);
        switch (numberOfRoots) {
            case 2 -> {
                System.out.printf("The equation has two roots %.5f and %.5f\n", roots[0], roots[1]);
            }
            case 1 -> {
                System.out.printf("The equation has one root %.5f\n", roots[0]);
            }
            case 0 -> {
                System.out.println("The equation has no real roots");
            }
        }
    }

    public static int solveQuadratic(double[] eqn, double[] roots) {
        double a = eqn[0];
        double b = eqn[1];
        double c = eqn[2];
        double d = Math.pow(b, 2) - 4 * a * c;
        double r1 = (-b + Math.sqrt(d)) / 2 * a;
        if (d > 0) {
            double r2 = (-b - Math.sqrt(d)) / 2 * a;
            roots[0] = r1;
            roots[1] = r2;
            return 2;
        } else if (d == 0) {
            roots[0] = r1;
            return 1;
        } else {
            return 0;
        }
    }

    /*
        (Strictly identical arrays) The arrays list1 and list2 are strictly identical
        if their corresponding elements are equal. Write a method that returns true if
        list1 and list2 are strictly identical, using the following header:
        public static boolean equals(int[] list1, int[] list2)
        Write a test program that prompts the user to enter two lists of integers and displays
        whether the two are strictly identical. Here are the sample runs. Note the first num-
        ber in the input for each list indicates the number of the elements in the list. This
        number is not part of the list.
            Enter list1 size and contents: 5 2 5 6 1 6
            Enter list2 size and contents: 5 2 5 6 1 6
            Two lists are strictly identical
            Enter list1 size and contents: 5 2 5 6 6 1
            Enter list2 size and contents: 5 2 5 6 1 6
            Two lists are not strictly identical
     */
    public static void ch7_26() {
        System.out.print("Enter list1 size and contents: ");
        int size = scanner.nextInt();
        int[] list1 = new int[size];
        for (int i = 0; i < size; i++) {
            list1[i] = scanner.nextInt();
        }
        System.out.print("Enter list2 size and contents: ");
        size = scanner.nextInt();
        int[] list2 = new int[size];
        for (int i = 0; i < size; i++) {
            list2[i] = scanner.nextInt();
        }
        if (strictlyEquals(list1, list2)) {
            System.out.println("Two lists are strictly identical");
        } else {
            System.out.println("Two lists are not strictly identical");
        }
    }

    public static boolean strictlyEquals(int[] list1, int[] list2) {
        if (list1.length != list2.length) return false;
        for (int i = 0; i < list1.length; i++) {
            if (list1[i] != list2[i]) return false;
        }
        return true;
    }

    /*
        (Identical arrays) The arrays list1 and list2 are identical if they have the same
        contents. Write a method that returns true if list1 and list2 are identical,
        using the following header:
        public static boolean equals(int[] list1, int[] list2)
        Write a test program that prompts the user to enter two lists of integers and dis-
        plays whether the two are identical. Here are the sample runs. Note the first num-
        ber in the input for each list indicates the number of the elements in the list. This
        number is not part of the list.
            Enter list1 size and contents: 5 2 5 6 6 1
            Enter list2 size and contents: 5 5 2 6 1 6
            Two lists are identical
            Enter list1: 5 5 5 6 6 1
            Enter list2: 5 2 5 6 1 6
            Two lists are not identical
     */
    public static void ch7_27() {
        System.out.print("Enter list1 size and contents: ");
        int size = scanner.nextInt();
        int[] list1 = new int[size];
        for (int i = 0; i < size; i++) {
            list1[i] = scanner.nextInt();
        }
        System.out.print("Enter list2 size and contents: ");
        size = scanner.nextInt();
        int[] list2 = new int[size];
        for (int i = 0; i < size; i++) {
            list2[i] = scanner.nextInt();
        }
        if (equals(list1, list2)) {
            System.out.println("Two lists are identical");
        } else {
            System.out.println("Two lists are not identical");
        }
    }

    public static boolean equals(int[] list1, int[] list2) {
        int[] sortedList1 = Arrays.copyOf(list1, list1.length);
        int[] sortedList2 = Arrays.copyOf(list2, list2.length);
        Arrays.sort(sortedList1);
        Arrays.sort(sortedList2);
        return strictlyEquals(sortedList1, sortedList2);
    }

    /*
        (Math: combinations) Write a program that prompts the user to enter 10 integers
        and displays all combinations of picking two numbers from the 10 numbers.
     */
    public static void ch7_28() {
        final int NUMBERS = 10;
        int[] numbers = ArrayUtils.inputIntArray(NUMBERS, String.format("Enter %d integers: ", NUMBERS));
        String[] combinations = new String[0];
        for (int i = 0; i < NUMBERS; i++) {
            for (int j = 0; j < NUMBERS; j++) {
                if (i == j) continue;
                String combination = numbers[i] + " " + numbers[j];
                boolean isNew = true;
                for (String s : combinations) {
                    if (s.equals(combination)) {
                        isNew = false;
                        break;
                    }
                }
                if (isNew) {
                    combinations = Arrays.copyOf(combinations, combinations.length + 1);
                    combinations[combinations.length - 1] = combination;
                }
            }
        }
        Arrays.stream(combinations).forEach(System.out::println);
    }

    /*
        (Game: pick four cards) Write a program that picks four cards from a deck of 52
        cards and computes their sum. An Ace, King, Queen, and Jack represent 1, 13, 12,
        and 11, respectively. Your program should display the number of picks that yields
        the sum of 24.
     */
    public static void ch7_29() {
        final int CARDS = 4;
        final int EXPECTED_SUM = 24;
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] deck = new int[52];
        // Initialize cards
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i;
        }
        // Shuffle the cards
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);
            int temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
        int picks = 0;
        int[] currentPick = new int[CARDS];

        while (true) {
            for (int i = 0; i < CARDS; i++) {
                currentPick[i] = (int) (Math.random() * deck.length);
            }
            int sum = Arrays.stream(currentPick).map(x -> x % 13 + 1).reduce(0, Integer::sum);
            if (sum == EXPECTED_SUM) break;
            picks++;
        }
        for (int card : currentPick) {
            System.out.printf("%s of %s\n", ranks[card % 13], suits[card / 13]);
        }
        System.out.println("Number of picks: " + picks);
    }

    /*
        (Pattern recognition: consecutive four equal numbers) Write the following method
        that tests whether the array has four consecutive numbers with the same value:
        public static boolean isConsecutiveFour(int[] values)
        Write a test program that prompts the user to enter a series of integers and dis-
        plays it if the series contains four consecutive numbers with the same value. Your
        program should first prompt the user to enter the input size—i.e., the number of
        values in the series. Here are sample runs:
            Enter the number of values: 8
            Enter the values: 3 4 5 5 5 5 4 5
            The list has consecutive fours
            Enter the number of values: 9
            Enter the values: 3 4 5 5 6 5 5 4 5
            The list has no consecutive fours
     */
    public static void ch7_30() {
        System.out.print("Enter the number of values: ");
        int size = scanner.nextInt();
        int[] values = ArrayUtils.inputIntArray(size, "Enter the values: ");
        if (isConsecutiveFour(values)) {
            System.out.println("The list has consecutive fours");
        } else {
            System.out.println("The list has no consecutive fours");
        }
    }

    public static boolean isConsecutiveFour(int[] values) {
        int count = 1;
        for (int i = 1; i < values.length - 2; i++) {
            count = values[i] == values[i - 1] ? count + 1 : 1;
            if (count == 4) return true;
        }
        return false;
    }

    /*
        (Merge two sorted lists) Write the following method that merges two sorted lists
        into a new sorted list:
        public static int[] merge(int[] list1, int[] list2)
        Implement the method in a way that takes at most list1.length + list2.
        length comparisons. See liveexample.pearsoncmg.com/dsanimation/
        MergeSortNeweBook.html for an animation of the implementation. Write a test
        program that prompts the user to enter two sorted lists and displays the merged
        list. Here is a sample run. Note the first number in the input indicates the number
        of the elements in the list. This number is not part of the list.
        Enter list1 size and contents: 5 1 5 16 61 111
        Enter list2 size and contents: 4 2 4 5 6
        list1 is 1 5 16 61 111
        list2 is 2 4 5 6
        The merged list is 1 2 4 5 5 6 16 61 111
     */
    public static void ch7_31() {
        System.out.print("Enter list1 size and contents: ");
        int size = scanner.nextInt();
        int[] list1 = new int[size];
        for (int i = 0; i < size; i++) {
            list1[i] = scanner.nextInt();
        }
        System.out.print("Enter list2 size and contents: ");
        size = scanner.nextInt();
        int[] list2 = new int[size];
        for (int i = 0; i < size; i++) {
            list2[i] = scanner.nextInt();
        }
        int[] merged = merge(list1, list2);
        System.out.print("list1 is ");
        printList(list1);
        System.out.print("list2 is ");
        printList(list2);
        System.out.print("The merged list is ");
        printList(merged);

    }

    public static void printList(int[] list) {
        for (int value : list) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static int[] merge(int[] list1, int[] list2) {
        int[] result = new int[list1.length + list2.length];
        int index1 = 0;
        int index2 = 0;

        while (index1 <= list1.length - 1 || index2 <= list2.length - 1) {
            if (index1 == list1.length) {
                result[index1 + index2] = list2[index2];
                index2++;
            } else if (index2 == list2.length) {
                result[index1 + index2] = list1[index1];
                index1++;
            } else if (list1[index1] < list2[index2]) {
                result[index1 + index2] = list1[index1];
                index1++;
            } else {
                result[index1 + index2] = list2[index2];
                index2++;
            }
        }
        return result;
    }

    /*
        (Partition of a list) Write the following method that partitions the list using the
        first element, called a pivot:
        public static int partition(int[] list)
        After the partition, the elements in the list are rearranged so all the elements before
        the pivot are less than or equal to the pivot, and the elements after the pivot are
        greater than the pivot. The method returns the index where the pivot is located in
        the new list. For example, suppose the list is {5, 2, 9, 3, 6, 8}. After the partition,
        the list becomes {3, 2, 5, 9, 6, 8}. Implement the method in a way that takes at
        most list.length comparisons. See liveexample.pearsoncmg.com/dsanima-
        tion/QuickSortNeweBook.html for an animation of the implementation. Write a
        test program that prompts the user to enter the size of the list and the contents of
        the list and displays the list after the partition. Here is a sample run.
        Enter list size: 8
        Enter list content: 10 1 5 16 61 9 11 1
        After the partition, the list is 9 1 5 1 10 61 11 16
     */
    public static void ch7_32() {
        System.out.print("Enter list size: ");
        int size = scanner.nextInt();
        int[] list = new int[size];
        System.out.print("Enter list content: ");
        for (int i = 0; i < size; i++) {
            list[i] = scanner.nextInt();
        }
        int pivotIndex = partition(list);
        System.out.print("After the partition, the list is ");
        printList(list);
        System.out.print("Pivot index is " + pivotIndex);
    }

    public static void test_ch7_32() {
        for (int j = 0; j < 100_000; j++) {
            int size = 16;
            int[] list = new int[size];
            for (int i = 0; i < size; i++) {
                list[i] = (int) (Math.random() * 101);
            }
            int[] originalList = Arrays.copyOf(list, list.length);
            int pivot = list[0];
            int pivotIndex = partition(list);
            try {
                assert list[pivotIndex] == pivot;

                for (int i = 0; i < pivotIndex; i++) {
                    assert list[i] <= pivot;
                }

                for (int i = pivotIndex + 1; i < list.length; i++) {
                    assert list[i] > pivot;
                }

                Set<Integer> originalElements = Arrays.stream(originalList).boxed().collect(Collectors.toSet());
                Set<Integer> partitionElements = Arrays.stream(list).boxed().collect(Collectors.toSet());
                assert originalElements.equals(partitionElements);
            } catch (AssertionError e) {
                System.out.println();
                System.out.print("Original list: ");
                printList(originalList);
                System.out.print("After the partition, the list is ");
                printList(list);
                System.out.print("Pivot index is " + pivotIndex);
                System.out.println();
            }
        }
    }

    public static int partition(int[] list) {
        int pivot = list[0];
        int low = 1;
        int high = list.length - 1;
        while (low != high) {
            if (list[low] <= pivot) {
                low++;
            } else if (list[high] > pivot) {
                high--;
            } else {
                int temp = list[low];
                list[low] = list[high];
                list[high] = temp;
            }
        }
        while (high > 0 && list[high] > pivot) {
            high--;
        }
        if (pivot >= list[high]) {
            list[0] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return 0;
        }

    }

    /*
        (Culture: Chinese Zodiac) Simplify Listing 3.9 using an array of strings to store
        the animal names.
     */
    public static void ch7_33() {
        String[] zodiac = {
                "monkey", "rooster", "dog", "pig",
                "rat", "ox", "tiger", "rabbit",
                "dragon", "snake", "horse", "sheep"
        };
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        System.out.println(zodiac[year % 12]);
    }

    /*
        (Sort characters in a string) Write a method that returns a sorted string using the
        following header:
        public static String sort(String s)
        For example, sort("acb") returns abc.
        Write a test program that prompts the user to enter a string and displays the sorted
        string.
     */
    public static void ch7_34() {
        System.out.print("Enter a string: ");
        String input = scanner.next();
        System.out.println(sort(input));
    }

    public static String sort(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return new String(chars);
    }

    /*
        (Game: hangman) Write a hangman game that randomly generates a word and
        prompts the user to guess one letter at a time, as presented in the sample run.
        Each letter in the word is displayed as an asterisk. When the user makes a correct
        guess, the actual letter is then displayed. When the user finishes a word, display
        the number of misses and ask the user whether to continue to play with another
        word. Declare an array to store words, as follows:
        // Add any words you wish in this array
        String[] words = {"write", "that",...};
            (Guess) Enter a letter in word ******* > p
            (Guess) Enter a letter in word p****** > r
            (Guess) Enter a letter in word pr**r** > p
            p is already in the word
            (Guess) Enter a letter in word pr**r** > o
            (Guess) Enter a letter in word pro*r** > g
            (Guess) Enter a letter in word progr** > n
            n is not in the word
            (Guess) Enter a letter in word progr** > m
            (Guess) Enter a letter in word progr*m > a
            The word is program. You missed 1 time
            Do you want to guess another word? Enter y or n>
     */
    public static void ch7_35() {
        HangmanGame.start();
    }

    class HangmanGame {
        private static String word;
        private static char[] chars;
        private static boolean[] guessed;
        private static int missed;

        private static void start() {
            while (true) {
                word = generateWord();
                chars = word.toCharArray();
                guessed = new boolean[chars.length];
                missed = 0;
                System.out.println(word);
                while (!allGuessed()) {
                    System.out.printf("(Guess) Enter a letter in word %s > ", getWord());
                    char guess = scanner.next().charAt(0);
                    boolean isMiss = true;
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] == guess && guessed[i]) {
                            System.out.printf("%c is already in the word\n", guess);
                            isMiss = false;
                            break;
                        }
                        if (chars[i] == guess) {
                            guessed[i] = true;
                            isMiss = false;
                        }
                    }
                    if (isMiss) {
                        missed++;
                        System.out.printf("%c is not in the word\n", guess);
                    }
                }
                System.out.printf("The word is %s. You missed %d %s\n", getWord(), missed, missed == 1 ? "time" : "times");
                System.out.print("Do you want to guess another word? Enter y or n> ");
                char input = scanner.next().charAt(0);
                if (input == 'y') {
                    start();
                } else {
                    break;
                }
            }
        }

        public static String generateWord() {
            String[] words = {"cat", "tree", "book", "teapot", "bottle"};
            return words[(int) (Math.random() * words.length)];
        }

        public static String getWord() {
            char[] result = Arrays.copyOf(chars, chars.length);
            for (int i = 0; i < chars.length; i++) {
                if (!guessed[i]) {
                    result[i] = '*';
                }
            }

            return new String(result);
        }

        public static boolean allGuessed() {
            for (int i = 0; i < guessed.length; i++) {
                if (!guessed[i]) return false;
            }
            return true;
        }
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
