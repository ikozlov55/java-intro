package exercises.ch11.ex2;


public class Student extends Person {
    public static String FRESHMAN = "freshman";
    public static String SOPHOMORE = "sophomore";
    public static String JUNIOR = "junior";
    public static String SENIOR = "senior";

    private String status;

    public Student(String name, String address, String phoneNumber, String email) {
        super(name, address, phoneNumber, email);
        this.status = FRESHMAN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getName(), getName());
    }
}
