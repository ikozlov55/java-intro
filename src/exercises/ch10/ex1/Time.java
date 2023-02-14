package exercises.ch10.ex1;


public class Time {
    private int hour;
    private int minute;
    private int second;


    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Time(long elapseTime) {
        long totalSeconds = elapseTime / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        hour = (int) (totalHours % 24);
        minute = (int) (totalMinutes % 60);
        second = (int) (totalSeconds % 60);
    }

    public Time() {
        this(System.currentTimeMillis());
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setTime(long elapseTime) {
        Time time = new Time(elapseTime);
        hour = time.hour;
        minute = time.minute;
        second = time.second;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
