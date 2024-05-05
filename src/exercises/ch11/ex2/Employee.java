package exercises.ch11.ex2;

import exercises.ch10.ex14.MyDate;

public class Employee extends Person {
    private final String office;
    private final double salary;
    private final MyDate dateHired;

    public Employee(String name, String address, String phoneNumber, String email, String office, double salary) {
        super(name, address, phoneNumber, email);
        this.office = office;
        this.salary = salary;
        this.dateHired = new MyDate();
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getName(), getName());
    }
}
