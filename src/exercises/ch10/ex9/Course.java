package exercises.ch10.ex9;


import java.util.Arrays;

public class Course {

    private int capacity = 100;
    private final String courseName;
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
}
