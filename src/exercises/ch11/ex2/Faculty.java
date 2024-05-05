package exercises.ch11.ex2;

public class Faculty extends Employee {
    private final int officeHours;
    private final String rank;

    public Faculty(String name, String address, String phoneNumber, String email, String office,
                   double salary, int officeHours, String rank) {
        super(name, address, phoneNumber, email, office, salary);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getName(), getName());
    }
}
