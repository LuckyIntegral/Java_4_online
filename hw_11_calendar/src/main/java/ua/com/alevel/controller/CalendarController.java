package ua.com.alevel.controller;

import ua.com.alevel.entity.MyCalendar;
import ua.com.alevel.service.CalendarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalendarController {
    private final CalendarService service = new CalendarService();
    public void start() {
        options();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String option;
            do {
                option = reader.readLine();
                try {
                    switch (option) {
                        case "1" -> getCurrentTime();
                        case "2" -> getCurrentMillis();
                        case "3" -> addDateMenu(reader);
                        case "4" -> minusDateMenu(reader);
                        case "5" -> differencesMenu(reader);
                        case "6" -> exit();
                    }
                } catch (SecurityException e) {
                    System.out.println("Pay attention to the format you enter !!!");
                }
                options();
            } while (!option.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.out.println("Something went wrong with IO");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void options() {
        System.out.println("--------------------------MAIN-MENU--------------------------");
        System.out.println("To get current time, enter                                   1");
        System.out.println("To get current time in milliseconds, enter                   2");
        System.out.println("To add anything you want to date, enter                      3");
        System.out.println("To subtract anything you want to date, enter                 4");
        System.out.println("To find difference between two dates in any format, enter    5");
        System.out.println("To exit the program, enter                                   6");
        System.out.println("--------------------------------------------------------------");
    }

    private void differencesMenuOptions() {
        System.out.println("-----------------DIFFERENCES-MENU----------------");
        System.out.println("To get the difference in years, enter           1");
        System.out.println("To get the difference in months, enter          2");
        System.out.println("To get the difference in days, enter            3");
        System.out.println("To get the difference in hours, enter           4");
        System.out.println("To get the difference in minutes, enter         5");
        System.out.println("To get the difference in seconds, enter         6");
        System.out.println("To get the difference in milliseconds, enter    7");
        System.out.println("--------------------------------------------------");
    }

    private void differencesMenu(BufferedReader reader) {
        differencesMenuOptions();
        try {
            switch (reader.readLine()) {
                case "1" -> getDifferencesInYears(reader);
                case "2" -> getDifferencesInMonths(reader);
                case "3" -> getDifferencesInDays(reader);
                case "4" -> getDifferencesInHours(reader);
                case "5" -> getDifferencesInMinutes(reader);
                case "6" -> getDifferencesInSeconds(reader);
                case "7" -> getDifferencesInMilliseconds(reader);
            }
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInYears(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInYears(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInMonths(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInMonths(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInDays(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInDays(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInHours(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInHours(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInMinutes(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInMinutes(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInSeconds(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInSeconds(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void getDifferencesInMilliseconds(BufferedReader reader) {
        try {
            MyCalendar calendar1 = getNewMyCalendar(reader);
            MyCalendar calendar2 = getSecondNewMyCalendar(reader);
            System.out.println("Your difference is:");
            System.out.println(service.differenceInMilliseconds(calendar1, calendar2));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusDateMenu(BufferedReader reader) {
        minusDateMenuOptions();
        try {
            switch (reader.readLine()) {
                case "1" -> minusDates(reader);
                case "2" -> minusYearsToDate(reader);
                case "3" -> minusMonthsToDate(reader);
                case "4" -> minusDaysToDate(reader);
                case "5" -> minusHoursToDate(reader);
                case "6" -> minusMinutesToDate(reader);
                case "7" -> minusSecondsToDate(reader);
                case "8" -> minusMillisecondsToDate(reader);
            }
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusDateMenuOptions() {
        System.out.println("----------------MINUS-MENU-OPTIONS---------------");
        System.out.println("To subtract a date from your date, enter        1");
        System.out.println("To subtract years from your date, enter         2");
        System.out.println("To subtract months from your date, enter        3");
        System.out.println("To subtract days from your date, enter          4");
        System.out.println("To subtract hours from your date, enter         5");
        System.out.println("To subtract minutes from your date, enter       6");
        System.out.println("To subtract seconds from your date, enter       7");
        System.out.println("To subtract milliseconds from your date, enter  8");
        System.out.println("-------------------------------------------------");
    }

    private void minusDates(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            MyCalendar calendar1 = getSecondNewMyCalendar(reader);
            System.out.println(new MyCalendar(calendar1.getTimeMillis() - calendar.getTimeMillis()));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusMillisecondsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of milliseconds to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusMilliseconds(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusSecondsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of seconds to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusSeconds(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusMinutesToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of minutes to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusMinutes(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusHoursToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of hours to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusHours(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusDaysToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of days to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusDays(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusMonthsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of months to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusMonths(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void minusYearsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of years to subtract");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.minusYears(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addDateMenu(BufferedReader reader) {
        addDateMenuOptions();
        try {
            switch (reader.readLine()) {
                case "1" -> addDates(reader);
                case "2" -> addYearsToDate(reader);
                case "3" -> addMonthsToDate(reader);
                case "4" -> addDaysToDate(reader);
                case "5" -> addHoursToDate(reader);
                case "6" -> addMinutesToDate(reader);
                case "7" -> addSecondsToDate(reader);
                case "8" -> addMillisecondsToDate(reader);
            }
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addDateMenuOptions() {
        System.out.println("--------------ADD-MENU-OPTIONS--------------");
        System.out.println("To add date to your date, enter            1");
        System.out.println("To add years to your date, enter           2");
        System.out.println("To add months to your date, enter          3");
        System.out.println("To add days to your date, enter            4");
        System.out.println("To add hours to your date, enter           5");
        System.out.println("To add minutes to your date, enter         6");
        System.out.println("To add seconds to your date, enter         7");
        System.out.println("To add milliseconds to your date, enter    8");
        System.out.println("--------------------------------------------");
    }

    private void addMillisecondsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of milliseconds to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addMilliseconds(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addSecondsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of seconds to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addSeconds(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addMinutesToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of minutes to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addMinutes(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addHoursToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of hours to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addHours(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addDaysToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of days to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addDays(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addMonthsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of months to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addMonths(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addYearsToDate(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            System.out.println("Enter the number of years to add");
            int y = Integer.parseInt(reader.readLine());
            System.out.println(service.addYears(calendar, y));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private void addDates(BufferedReader reader) {
        try {
            MyCalendar calendar = getNewMyCalendar(reader);
            MyCalendar calendar1 = getSecondNewMyCalendar(reader);
            System.out.println(new MyCalendar(calendar1.getTimeMillis() + calendar.getTimeMillis()));
        } catch (SecurityException e) {
            System.out.println("Pay attention to the format you enter !!!");
        } catch (Exception e) {
            System.out.println("Unknown exception");
        }
    }

    private MyCalendar getSecondNewMyCalendar(BufferedReader reader) {
        try {
            System.out.println("Please enter the second date below in the same format");
            String line = reader.readLine().trim();
            return new MyCalendar(line);
        } catch (Exception e) {
            throw new SecurityException();
        }
    }

    private MyCalendar getNewMyCalendar(BufferedReader reader) {
        try {
            System.out.println("Please enter the date below in one of the following formats:");
            System.out.println("hint: yyyy - year, MM - month, dd - day, hh - hour");
            System.out.println("      mm - minute, ss - seconds, SSS - milliseconds");
            System.out.println("formats: yyyy:MM:dd");
            System.out.println("         yyyy:MM:dd hh:mm");
            System.out.println("         yyyy:MM:dd hh:mm:ss");
            System.out.println("         yyyy:MM:dd hh:mm:ss SSS");
            System.out.println("or enter the number of milliseconds");
            String line = reader.readLine().trim();
            return new MyCalendar(line);
        } catch (Exception e) {
            throw new SecurityException();
        }
    }

    private void getCurrentTime() {
        System.out.println(service.now());
    }

    private void getCurrentMillis() {
        System.out.println(service.now().getTimeMillis());
    }

    private void exit() {
        System.exit(0);
    }
}
