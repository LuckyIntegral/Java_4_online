package ua.com.alevel.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyCalendar {
    private int milliseconds;
    private int seconds;
    private int minutes;
    private int hours;
    private int days;
    private int months;
    private long year;
    private boolean leapYear;
    private long millis;
    private final static int[] daysInYear = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] daysInLeapYear = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] daysInYearSum = new int[] {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    private static final int[] daysInLeapYearSum = new int[] {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366};

    public MyCalendar() { // current time
        this(62_168_518_800_000L + System.currentTimeMillis());
    }

    public MyCalendar(String format) {
        try {
            set(format.trim());
        } catch (Exception e) {
            throw new SecurityException();
        }
    }

    public MyCalendar(long time) {
        configMyCalendar(time);
    }

    private void configMyCalendar(long time) {
        if (time < 0) { time = 0; }
        this.millis = time;
        setMilliseconds((int) (time % 1000L));
        time = time / 1000L;
        setSeconds((int) (time % 60));
        time = time / 60L;
        setMinutes((int) (time % 60L));
        time = time / 60L;
        setHours((int) (time % 24));
        time = time / 24L;
        long fullFourYearsCycles = time / 1461;
        time = time % 1461;
        setYear(fullFourYearsCycles * 4 + (time < 366 ? 0 : (time < 731 ? 1 : (time < 1096 ? 2 : 3))));
        if (time < 366) {
            setDaysAndMonths((int) time);
        } else {
            time = time - 366;
            setDaysAndMonths((int) (time % 365));
        }
    }

    @Override
    public String toString() { // 2023-02-02 13:56:37 777
        return String.format("%d-%02d-%02d %02d:%02d:%02d %03d", year, months, days, hours, minutes, seconds, milliseconds);
    }

    private void setMilliseconds(int millis) {
        if (millis >= 1000 || millis < 0) {
            throw new SecurityException();
        }
        this.milliseconds = millis;
    }

    private void setSeconds(int seconds) {
        if (seconds >= 60 || seconds < 0) {
            throw new SecurityException();
        }
        this.seconds = seconds;
    }

    private void setMinutes(int minutes) {
        if (minutes >= 60 || minutes < 0) {
            throw new SecurityException();
        }
        this.minutes = minutes;
    }

    private void setHours(int hours) {
        if (hours >= 24 || hours < 0) {
            throw new SecurityException();
        }
        this.hours = hours;
    }

    public void addYear(int year) {
        this.year += year;
    }

    public void minusYear(int year) {
        this.year -= year;
    }

    public void addMonth() {
        this.configMyCalendar(millis + ((leapYear ? daysInLeapYear[months] : daysInYear[months]) * 86400000L));
    }

    public void minusMonth() {
        this.configMyCalendar(millis - ((leapYear ? daysInLeapYear[months] : daysInYear[months]) * 86400000L));
    }

    public void addDay(int days) {
        this.configMyCalendar(millis + (days * 86_400_000L));
    }

    public void minusDay(int days) {
        this.configMyCalendar(millis - (days * 86_400_000L));
    }

    public void addHour(int hours) {
        this.configMyCalendar(millis + (hours * 3600000L));
    }

    public void minusHour(int hours) {
        this.configMyCalendar(millis - (hours * 3600000L));
    }

    public void addMinutes(int minutes) {
        this.configMyCalendar(millis + (minutes * 60000L));
    }

    public void minusMinutes(int minutes) {
        this.configMyCalendar(millis - (minutes * 60000L));
    }

    public void addSeconds(int seconds) {
        this.configMyCalendar(millis + (seconds * 1000L));
    }

    public void minusSeconds(int seconds) {
        this.configMyCalendar(millis - (seconds * 1000L));
    }

    public void addMilliseconds(int seconds) {
        this.configMyCalendar(millis + seconds);
    }

    public void minusMilliseconds(int seconds) {
        this.configMyCalendar(millis - seconds);
    }

    private void setDaysAndMonths(int days) {
        days++;
        if (days <= 0) {
            throw new SecurityException();
        }
        int[] months = leapYear ? daysInLeapYearSum : daysInYearSum;
        int i;
        for (i = 1; i < months.length; i++) {
            if (days <= months[i]) {
                break;
            }
        }
        this.months = i;
        this.days = days - months[i - 1];
    }

    private void setYear(long year) {
        leapYear = year % 4L == 0;
        this.year = year;
    }

    public static MyCalendar now() {
        return new MyCalendar();
    }

    public long getTimeMillis() {
        return millis;
    }

    public void set(long year, int month) {
        set(year, month, 0, 0,0);
    }

    public void set(long year, int month, int day, int hour, int minutes) {
        set(year, month, day, hour, minutes, 0, 0);
    }

    public void set(long year, int month, int day, int hour, int minutes, int seconds, int milliseconds) {
        setYear(year);
        if (month > 12 || month < 1) {
            throw new SecurityException();
        }
        setDaysAndMonths((leapYear ? daysInLeapYearSum[month] : daysInYearSum[month]) + day);
        setHours(hour);
        setMinutes(minutes);
        setSeconds(seconds);
        setMilliseconds(milliseconds);
    }
    public void set(String format) throws ParseException {
        if (format.matches("^\\d+$")) {
            configMyCalendar(Long.parseLong(format));
        } else if (format.matches("^\\d+-\\d{1,2}-\\d{1,2}$")) { //2023-02-02
            configMyCalendar(62_168_518_800_000L + new SimpleDateFormat("y-M-d").parse(format).getTime());
        } else if (format.matches("^\\d+-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$")) { //2023-02-02 13:56
            configMyCalendar(62_168_518_800_000L + new SimpleDateFormat("y-M-d k:m").parse(format).getTime());
        } else if (format.matches("^\\d+-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$")) { //2023-02-02 13:56:37
            configMyCalendar(62_168_518_800_000L + new SimpleDateFormat("y-M-d k:m:s").parse(format).getTime());
        } else if (format.matches("^\\d+-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2} \\d{1,3}$")) { //2023-02-02 13:56:37 777
            configMyCalendar(62_168_518_800_000L + new SimpleDateFormat("y-M-d k:m:s S").parse(format).getTime());
        } else {
            throw new SecurityException();
        }
    }

    public int getMonths() {
        return months;
    }

    public long getYear() {
        return year;
    }
}
