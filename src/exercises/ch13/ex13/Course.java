package exercises.ch13.ex13;

import java.util.Arrays;

public class Course implements Cloneable {
    private int capacity = 100;
    private String courseName;
    private String[] students = new String[capacity];
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        if (numberOfStudents >= students.length) {
            capacity *= 2;
            students = Arrays.copyOf(students, capacity);
        }
        students[numberOfStudents] = student;
        numberOfStudents++;
    }

    public String[] getStudents() {
        return Arrays.copyOf(students, numberOfStudents);
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        if (Arrays.stream(students).findFirst().isPresent()) {
            students = Arrays.stream(students)
                    .filter(x -> x == null || !x.equals(student))
                    .toArray(String[]::new);
            numberOfStudents--;
        }
    }

    public void clear() {
        students = new String[capacity];
        numberOfStudents = 0;
    }

    @Override
    public Course clone() {
        try {
            Course clone = (Course) super.clone();
            clone.students = students.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
