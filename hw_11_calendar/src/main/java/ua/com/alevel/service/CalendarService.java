package ua.com.alevel.service;

import ua.com.alevel.entity.MyCalendar;

public class CalendarService {
    public MyCalendar now() {
        return MyCalendar.now();
    }

    public MyCalendar addYears(MyCalendar calendar1, int years) {
        calendar1.addYear(years);
        return calendar1;
    }
    public MyCalendar addMonths(MyCalendar calendar, int months) {
        for (int i = 0; i < months; i++) {
            calendar.addMonth();
        }
        return calendar;
    }

    public MyCalendar addDays(MyCalendar calendar, int days) {
        calendar.addDay(days);
        return calendar;
    }

    public MyCalendar addHours(MyCalendar calendar, int hours) {
        calendar.addHour(hours);
        return calendar;
    }

    public MyCalendar addMinutes(MyCalendar calendar, int minutes) {
        calendar.addMinutes(minutes);
        return calendar;
    }

    public MyCalendar addSeconds(MyCalendar calendar, int seconds) {
        calendar.addSeconds(seconds);
        return calendar;
    }

    public MyCalendar addMilliseconds(MyCalendar calendar, int milliseconds) {
        calendar.addMilliseconds(milliseconds);
        return calendar;
    }

    public MyCalendar minusYears(MyCalendar calendar, int years) {
        calendar.minusYear(years);
        return calendar;
    }

    public MyCalendar minusMonths(MyCalendar calendar, int months) {
        for (int i = 0; i < months; i++) {
            calendar.minusMonth();
        }
        return calendar;
    }

    public MyCalendar minusDays(MyCalendar calendar, int days) {
        calendar.minusDay(days);
        return calendar;
    }

    public MyCalendar minusHours(MyCalendar calendar, int hours) {
        calendar.minusHour(hours);
        return calendar;
    }

    public MyCalendar minusMinutes(MyCalendar calendar, int minutes) {
        calendar.minusMinutes(minutes);
        return calendar;
    }

    public MyCalendar minusSeconds(MyCalendar calendar, int seconds) {
        calendar.minusSeconds(seconds);
        return calendar;
    }

    public MyCalendar minusMilliseconds(MyCalendar calendar, int milliseconds) {
        calendar.minusMilliseconds(milliseconds);
        return calendar;
    }

    public long differenceInYears(MyCalendar first, MyCalendar second) {
        return first.getYear() - second.getYear();
    }

    public long differenceInMonths(MyCalendar first, MyCalendar second) {
        return ((first.getYear() * 12) + first.getMonths()) - ((second.getYear() * 12) + second.getMonths());
    }

    public long differenceInDays(MyCalendar first, MyCalendar second) {
        return (first.getTimeMillis() - second.getTimeMillis()) / 86_400_000L;
    }

    public long differenceInHours(MyCalendar first, MyCalendar second) {
        return (first.getTimeMillis() - second.getTimeMillis()) / 3_600_000L;
    }

    public long differenceInMinutes(MyCalendar first, MyCalendar second) {
        return (first.getTimeMillis() - second.getTimeMillis()) / 60_000L;
    }

    public long differenceInSeconds(MyCalendar first, MyCalendar second) {
        return (first.getTimeMillis() - second.getTimeMillis()) / 1000L;
    }

    public long differenceInMilliseconds(MyCalendar first, MyCalendar second) {
        return first.getTimeMillis() - second.getTimeMillis();
    }
}
