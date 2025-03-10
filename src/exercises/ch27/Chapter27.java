package exercises.ch27;

import exercises.ch27.ex1.MyHashMapLP;
import exercises.ch27.ex2.MyHashMapQP;
import exercises.ch27.ex3.MyHashMapDH;
import exercises.ch27.ex4.MyHashMapDK;
import exercises.ch27.ex5.MyHashSetHM;
import exercises.ch27.ex6.Exercise27_06;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Chapter27 {

    /*
        (Implement MyMap using open addressing with linear probing) Create a new con-
        crete class that implements MyMap using open addressing with linear probing. For
        simplicity, use f(key) = key % size as the hash function, where size is the
        hash-table size. Initially, the hash-table size is 4. The table size is doubled when-
        ever the load factor exceeds the threshold (0.5). Test your new MyHashMap class
        using the code at https://liveexample.pearsoncmg.com/test/Exercise27_01.txt.
     */
    public static void ex1() {
        MyHashMapLP<Integer, Integer> map = new MyHashMapLP<>();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter integer keys, input ends with a negative value: ");
        int key = input.nextInt();
        while (key >= 0) {
            map.put(key, key);
            key = input.nextInt();
        }

        System.out.print("Enter key1: ");
        int key1 = input.nextInt();
        System.out.println("Is " + key1 + " in the map? " + map.containsKey(key1));

        System.out.print("Enter key2: ");
        int key2 = input.nextInt();
        System.out.println("Is " + key2 + " in the map? " + map.containsKey(key2));

        System.out.println("The map size is " + map.size());

        map.remove(2);
        System.out.println("After removing key 2 from the map, is key 2 in the map? " + map.containsKey(2));
        System.out.println("The map size is " + map.size());
    }

    /*
        (Implement MyMap using open addressing with quadratic probing) Create a new
        concrete class that implements MyMap using open addressing with quadratic prob-
        ing. For simplicity, use f(key) = key % size as the hash function, where size
        is the hash-table size. Initially, the hash-table size is 4. The table size is doubled
        whenever the load factor exceeds the threshold (0.5).
     */
    public static void ex2() {
        MyHashMapQP<Integer, Integer> map = new MyHashMapQP<>();
        int[] range = IntStream.rangeClosed(1, 10).toArray();
        for (int i : range) {
            map.put(i, i);
        }
        map.put(2, 4);
        map.put(3, 9);
        System.out.println(map);
        for (int i = 0; i < 12; i++) {
            System.out.printf("map[%d] = %d, contains %d = %b\n", i, map.get(i), i, map.containsKey(i));
        }
        for (int i : range) {
            map.remove(i);
        }
        System.out.println(map);
    }

    /*
        (Implement MyMap using open addressing with double hashing) Create a new
        concrete class that implements MyMap using open addressing with double hashing.
        For simplicity, use f(key) = key % size as the hash function, where size
        is the hash-table size. Initially, the hash-table size is 4. The table size is doubled
        whenever the load factor exceeds the threshold (0.5).
     */
    public static void ex3() {
        MyHashMapDH<Integer, Integer> map = new MyHashMapDH<>();
        int[] range = IntStream.rangeClosed(1, 10).toArray();
        for (int i : range) {
            map.put(i, i);
        }
        map.put(2, 4);
        map.put(3, 9);
        System.out.println(map);
        for (int i = 0; i < 12; i++) {
            System.out.printf("map[%d] = %d, contains %d = %b\n", i, map.get(i), i, map.containsKey(i));
        }
        for (int i : range) {
            map.remove(i);
        }
        System.out.println(map);
    }

    /*
        (Modify MyHashMap with duplicate keys) Modify MyHashMap to allow dupli-
        cate keys for entries. You need to modify the implementation for the put(key,
        value) method. Also add a new method named getAll(key) that returns a set
        of values that match the key in the map.
     */
    public static void ex4() {
        MyHashMapDK<Integer, Integer> map = new MyHashMapDK<>();
        int[] range = IntStream.rangeClosed(1, 10).toArray();
        for (int i : range) {
            map.put(i, i);
        }
        map.put(2, 4);
        map.put(3, 9);
        System.out.println(map);
        for (int i = 0; i < 12; i++) {
            System.out.printf("map[%d] = %d, contains %d = %b, getAll = %s\n",
                    i, map.get(i), i, map.containsKey(i), map.getAll(i));
        }
        for (int i : range) {
            map.remove(i);
        }
        System.out.println(map);
    }

    /*
        (Implement MyHashSet using MyHashMap) Implement MyHashSet using MyHash-
        Map. Note you can create entries with (key, key), rather than (key, value).
     */
    public static void ex5() {
        Collection<String> set = new MyHashSetHM<>();
        set.add("Smith");
        set.add("Anderson");
        set.add("Lewis");
        set.add("Cook");
        set.add("Smith");

        System.out.println("Elements in set: " + set);
        System.out.println("Number of elements in set: " + set.size());
        System.out.println("Is Smith in set? " + set.contains("Smith"));

        set.remove("Smith");
        System.out.print("Names in set in uppercase are ");
        for (String s : set)
            System.out.print(s.toUpperCase() + " ");

        set.clear();
        System.out.println("\nElements in set: " + set);
    }

    /*
        (Animate linear probing) Write a program that animates linear probing, as shown
        in Figure 27.3. You can change the initial size of the hash table in the program.
        Assume the load-factor threshold is 0.75.
     */
    public static void ex6() {
        Exercise27_06.run();
    }

    /*

     */
    public static void ex7() {

    }

    /*

     */
    public static void ex8() {

    }

    /*

     */
    public static void ex9() {

    }

    /*

     */
    public static void ex10() {

    }

    /*

     */
    public static void ex11() {

    }

    /*

     */
    public static void ex12() {

    }

    /*

     */
    public static void ex13() {

    }

    /*

     */
    public static void ex14() {

    }

    /*

     */
    public static void ex15() {

    }
}
