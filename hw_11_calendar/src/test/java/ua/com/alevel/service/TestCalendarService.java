package ua.com.alevel.service;


import org.junit.jupiter.api.*;
import ua.com.alevel.entity.MyCalendar;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCalendarService {
    private final CalendarService service = new CalendarService();

    @Test
    public void now() {
        MyCalendar calendar = MyCalendar.now();
        Assertions.assertEquals(calendar.getMonths(), 2);
    }

    @Test
    public void addYears() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getYear();
        service.addYears(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getYear());
    }

    @Test
    public void addMonths() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getMonths();
        service.addMonths(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getMonths());
    }

    @Test
    public void addDays() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getDays();
        service.addDays(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getDays());
    }

    @Test
    public void addHours() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getHours();
        service.addHours(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getHours());
    }

    @Test
    public void addMinutes() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getMinutes();
        service.addMinutes(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getMinutes());
    }

    @Test
    public void addSeconds() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getSeconds();
        service.addSeconds(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getSeconds());
    }

    @Test
    public void addMilliseconds() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getMilliseconds();
        service.addMilliseconds(calendar, 1);
        Assertions.assertEquals(temp + 1, calendar.getMilliseconds());
    }

    @Test
    public void minusYears() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getYear();
        service.minusYears(calendar, 12);
        Assertions.assertEquals(temp - 12, calendar.getYear());
    }

    @Test
    public void minusMonths() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getYear();
        service.minusMonths(calendar, 12);
        Assertions.assertEquals(temp - 1, calendar.getYear());
    }

    @Test
    public void minusDays() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getYear();
        service.minusDays(calendar, 365);
        Assertions.assertEquals(temp - 1, calendar.getYear());
    }

    @Test
    public void minusHours() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getDays();
        service.minusHours(calendar, 24);
        Assertions.assertEquals(temp - 1, calendar.getDays());
    }

    @Test
    public void minusMinutes() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getDays();
        service.minusMinutes(calendar, 24 * 60);
        Assertions.assertEquals(temp - 1, calendar.getDays());
    }

    @Test
    public void minusSeconds() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getDays();
        service.minusSeconds(calendar, 24 * 60 * 60);
        Assertions.assertEquals(temp - 1, calendar.getDays());
    }

    @Test
    public void minusMilliseconds() {
        MyCalendar calendar = new MyCalendar();
        long temp = calendar.getDays();
        service.minusMilliseconds(calendar, 24 * 60 * 60 * 1000);
        Assertions.assertEquals(temp - 1, calendar.getDays());
    }

    @Test
    public void differenceInYears() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("1010-03-10 12:13:07 111");
        Assertions.assertEquals(service.differenceInYears(calendar1, calendar2), 1000);
    }

    @Test
    public void differenceInMonths() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2009-03-10 12:13:07 111");
        Assertions.assertEquals(service.differenceInMonths(calendar1, calendar2), 15);
    }

    @Test
    public void differenceInDays() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2010-06-10 12:13:07 111");
        Assertions.assertEquals(service.differenceInDays(calendar1, calendar2), 5);
    }

    @Test
    public void differenceInHours() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2010-06-14 12:13:07 111");
        Assertions.assertEquals(service.differenceInHours(calendar1, calendar2), 27);
    }

    @Test
    public void differenceInMinutes() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2010-06-15 15:13:07 111");
        Assertions.assertEquals(service.differenceInMinutes(calendar1, calendar2), 20);
    }

    @Test
    public void differenceInSeconds() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2010-06-15 15:33:07 111");
        Assertions.assertEquals(service.differenceInSeconds(calendar1, calendar2), 20);
    }

    @Test
    public void differenceInMilliseconds() {
        MyCalendar calendar1 = new MyCalendar("2010-06-15 15:33:27 555");
        MyCalendar calendar2 = new MyCalendar("2010-06-15 15:33:07 111");
        Assertions.assertEquals(service.differenceInMilliseconds(calendar1, calendar2), 20 * 1000 + 444);
    }
}
