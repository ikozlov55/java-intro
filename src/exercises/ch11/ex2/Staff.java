package exercises.ch11.ex2;

public class Staff extends Employee {
    private String title;

    public Staff(String name, String address, String phoneNumber, String email, String office,
                 double salary, String title) {
        super(name, address, phoneNumber, email, office, salary);
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getName(), getName());
    }
}
