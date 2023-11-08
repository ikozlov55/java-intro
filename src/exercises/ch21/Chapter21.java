package exercises.ch21;

import exercises.ch21.ex11.Exercise21_11;
import exercises.ch21.ex5.SyntaxHighlighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Chapter21 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Perform set operations on hash sets) Create two linked hash sets {"George",
        "Jim", "John", "Blake", "Kevin", "Michael"} and {"George", "Katie",
        "Kevin", "Michelle", "Ryan"} and find their union, difference, and intersection.
        (You can clone the sets to preserve the original sets from being changed by
        these set methods.)
     */
    public static void ch21_1() {
        Set<String> set1 = new LinkedHashSet<>(List.of("George", "Jim", "John", "Blake", "Kevin", "Michael"));
        Set<String> set2 = new LinkedHashSet<>(List.of("George", "Katie", "Kevin", "Michelle", "Ryan"));
        Set<String> union = new LinkedHashSet<>(set1);
        union.addAll(set2);
        Set<String> difference = new LinkedHashSet<>(set1);
        difference.removeAll(set2);
        Set<String> intersection = new LinkedHashSet<>(set1);
        intersection.retainAll(set2);

        System.out.println(union);
        System.out.println(difference);
        System.out.println(intersection);
    }

    /*
        (Display nonduplicate words in ascending order) Write a program that reads
        words from a text file and displays all the nonduplicate words in ascending order.
        The text file is passed as a command-line argument.
     */
    public static void ch21_2(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        Set<String> words = new TreeSet<>();
        try (Scanner input = new Scanner(new File(args[0]))) {
            while (input.hasNext()) {
                words.add(input.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(words);
    }

    /*
        (Count the keywords in Java source code) Revise the program in Listing 21.7. If a
        keyword is in a comment or in a string, don’t count it. Pass the Java file name from
        the command line. Assume the Java source code is correct and line comments and
        paragraph comments do not overlap.
    */
    public static void ch21_3(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        File file = new File(args[0]);
        try {
            System.out.printf("The number of keywords in %s is %d\n", file.getName(), countKeywords(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int countKeywords(File file) throws Exception {
        String[] keywordString = {"abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"};
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;
        Scanner input = new Scanner(file);
        boolean inComment = false;
        boolean inString = false;

        while (input.hasNext()) {
            String word = input.next();
            if (word.contains("/*") && !word.endsWith("*/")) {
                inComment = true;
                continue;
            }
            if (inComment && word.contains("*/")) {
                inComment = false;
                continue;
            }
            if (!inComment && word.equals("\"") || (word.startsWith("\"") && !word.endsWith("\"") && !word.endsWith("\";"))) {
                inString = true;
                continue;
            }
            if (!inComment && inString && word.contains("\"")) {
                inString = false;
                continue;
            }
            if (keywordSet.contains(word) && !inComment && !inString) {
                System.out.println(word);
                count++;
            }
        }

        return count;
    }

    /*
        (Count consonants and vowels) Write a program that prompts the user to enter a
        text file name and displays the number of vowels and consonants in the file. Use
        a set to store the vowels A, E, I, O, and U
     */
    public static void ch21_4() {
        System.out.print("Enter a filename: ");
        String filename = scanner.nextLine();
        Set<Character> vowelsSet = Set.of('A', 'E', 'I', 'O', 'U');
        int vowels = 0;
        int consonants = 0;
        try (Scanner input = new Scanner(new File(filename))) {
            while (input.hasNext()) {
                String line = input.nextLine();
                for (Character c : line.toCharArray()) {
                    if (!Character.isLetter(c)) continue;
                    if (vowelsSet.contains(Character.toUpperCase(c))) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("Number of vowels: %d\n", vowels);
        System.out.printf("Number of consonants: %d\n", consonants);
    }

    /*
        (Syntax highlighting) Write a program that converts a Java file into an HTML
        file. In the HTML file, the keywords, comments, and literals are displayed in
        bold navy, green, and blue, respectively. Use the command line to pass a Java file
        and an HTML file. For example, the following command
        java Exercise21_05 Welcome.java Welcome.html
        converts Welcome.java into Welcome.html. Figure 21.8a shows a Java file. The
        corresponding HTML file is shown in Figure 21.8b.

        exercises/Chapter21.java exercises/Chapter21.html
     */
    public static void ch21_5(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise21_05 Welcome.java Welcome.html");
            System.exit(1);
        }
        SyntaxHighlighter highlighter = new SyntaxHighlighter(args[0], args[1]);
        highlighter.makeHTML();
    }

    /*
        (Count the occurrences of numbers entered) Write a program that reads an
        unspecified number of integers and finds the one that has the most occur-
        rences. The input ends when the input is 0. For example, if you entered 2
        3 40 3 5 4 -3 3 3 2 0, the number 3 occurred most often. If not one but
        several numbers have the most occurrences, all of them should be reported.
        For example, since 9 and 3 appear twice in the list 9 30 3 9 3 2 4, both occur-
        rences should be reported.
     */
    public static void ch21_6() {
        Map<Integer, Integer> numbers = new HashMap<>();
        System.out.print("Enter numbers: ");
        int input = -1;
        while (input != 0) {
            input = scanner.nextInt();
            if (numbers.containsKey(input)) {
                numbers.put(input, numbers.get(input) + 1);
            } else {
                numbers.put(input, 1);
            }
        }
        Integer maxKey = null;
        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
            if (maxKey == null || entry.getValue() > numbers.get(maxKey)) {
                maxKey = entry.getKey();
            }
        }
        ArrayList<Integer> maxKeys = new ArrayList<>();
        maxKeys.add(maxKey);
        for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
            if (Objects.equals(entry.getValue(), numbers.get(maxKey)) && !Objects.equals(entry.getKey(), maxKey)) {
                maxKeys.add(entry.getKey());
            }
        }
        System.out.printf("Numbers: %s have the most occurrences\n", maxKeys);
    }

    /*
        (Revise Listing 21.9, CountOccurrenceOfWords.java) Rewrite Listing 21.9 to
        display the words in ascending order of occurrence counts.
     */
    public static void ch21_7() {
        String text = "Good morning. Have a good class. Have a good visit. Have fun!";
        Map<String, Integer> map = new TreeMap<>();
        String[] words = text.split("[\\s+\\p{P}]");

        for (String word : words) {
            String key = word.toLowerCase();
            if (key.isEmpty()) continue;

            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                int value = map.get(key);
                value++;
                map.put(key, value);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();
        sortedEntries.forEach(e -> System.out.printf("%-10s %d\n", e.getKey(), e.getValue()));
    }

    /*
        (Count the occurrences of words in a text file) Rewrite Listing 21.9 to read
        the text from a text file. The text file is passed as a command-line argument.
        Words are delimited by whitespace characters, punctuation marks (,;.:?),
        quotation marks ('"), and parentheses. Count words in case-insensitive fash-
        ion (e.g., consider Good and good to be the same word). The words must
        start with a letter. Display the output in alphabetical order of words, with each
        word preceded by its occurrence count
     */
    public static void ch21_8(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise21_08 file.txt");
            System.exit(1);
        }
        Map<String, Integer> map = new TreeMap<>();
        try (Scanner input = new Scanner(new File(args[0]))) {
            while (input.hasNext()) {
                for (String word : input.nextLine().split("[\\s+\\p{P}=]")) {
                    if (word.isEmpty()) continue;
                    word = word.toLowerCase();
                    map.put(word, map.computeIfAbsent(word, k -> 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        map.forEach((k, v) -> System.out.printf("%-12s %d\n", k, v));
    }

    /*
        (Guess the capitals using maps) Rewrite Programming Exercise 8.37 to store
        pairs of each state and its capital in a map. Your program should prompt the
        user to enter a state and should display the capital for the state.
     */
    public static void ch21_9() {
        Map<String, String> questions = Map.of(
                "Alabama", "Montgomery",
                "Alaska", "Juneau",
                "Arizona", "Phoenix"
        );
        int count = 0;
        for (Map.Entry<String, String> question : questions.entrySet()) {
            System.out.printf("What is the capital of %s? ", question.getKey());
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(question.getValue())) {
                count++;
            }
        }
        System.out.printf("The correct count is %d\n", count);
    }

    /*
        (Count the occurrences of each keyword) Rewrite Listing 21.7, CountKeywords.java
        to read in a Java source-code file and count the occurrence of each keyword in the
        file, but don’t count the keyword if it is in a comment or in a string literal.
     */
    public static void ch21_10() {
        Set<String> keywords = new HashSet<>(List.of("abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum",
                "extends", "for", "final", "finally", "float", "goto",
                "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile",
                "while", "true", "false", "null"));
        int count = 0;
        System.out.print("Enter file: ");
        File file = new File(scanner.nextLine());
        boolean inComment = false;
        boolean inString = false;

        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String line = input.nextLine();
                if (line.startsWith("//")) continue;
                if (line.contains("//")) {
                    line = line.split("//")[0];
                }
                for (String word : line.split(" ")) {
                    if (!inString && word.chars().filter(x -> x == '"').count() == 1) {
                        inString = true;
                        continue;
                    }
                    if (inString && word.chars().filter(x -> x == '"').count() == 1) {
                        inString = false;
                        continue;
                    }
                    if (!inComment && word.contains("/*") && !word.contains("*/")) {
                        inComment = true;
                        continue;
                    }
                    if (inComment && word.contains("*/")) {
                        inComment = false;
                        continue;
                    }
                    if (!inString && !inComment && keywords.contains(word)) {
                        System.out.println(word);
                        count++;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("%s contains %d keywords\n", file.getName(), count);
    }

    /*
        (Baby name popularity ranking) Use the data files from Programming Exercise 12.31
        to write a program that enables the user to select a year, gender, and enter a name
        to display the ranking of the name for the selected year and gender, as shown in
        Figure 21.9. To achieve the best efficiency, create two arrays for boy’s names and
        girl’s names, respectively. Each array has 10 elements for 10 years. Each element
        is a map that stores a name and its ranking in a pair with the name as the key.
     */
    public static void ch21_11() {
        Exercise21_11.run();
    }


    /*

     */
    public static void ch21_12() {
    }

    /*

     */
    public static void ch21_13() {
    }

    /*

     */
    public static void ch21_14() {
    }

    /*

     */
    public static void ch21_15() {
    }

}
