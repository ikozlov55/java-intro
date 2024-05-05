package exercises.ch11.ex5;

import java.util.ArrayList;

public class Course {

    private final String courseName;
    private ArrayList<String> students;
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        students.add(student);
        numberOfStudents++;
    }

    public ArrayList<String> getStudents() {
        return new ArrayList<>(students);
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        students.remove(student);
        numberOfStudents--;
    }

    public void clear() {
        students = new ArrayList<>();
        numberOfStudents = 0;
    }
}
