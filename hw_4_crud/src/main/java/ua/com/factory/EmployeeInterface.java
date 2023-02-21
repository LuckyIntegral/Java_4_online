package ua.com.factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmployeeInterface {
    public void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose your option");
        String line;
        menu();
        while ((line = reader.readLine()) != null) {
            crud(reader, line);
        }
    }

    private void crud(BufferedReader reader, String line) throws Exception {
        switch (line) {
            case "1" -> createEmployee(reader);
            case "2" -> findEmployee(reader);
            case "3" -> findAll();
            case "4" -> updateEmployee(reader);
            case "5" -> deleteEmployee(reader);
            case "6" -> stop();
        }
        menu();
    }

    private void updateEmployee(BufferedReader reader) throws Exception {
        System.out.println("Please enter the employee's phone number");
        String number = reader.readLine();
        Employee employee = EmployeeStorage.getByPhoneNUmber(number);
        if (employee == null) {
            System.out.println("Number is invalid");
            return;
        }

        System.out.println("If you want to update the name, enter 1");
        System.out.println("If you want to update the surname, enter 2");
        System.out.println("If you want to update the age, enter 3");
        System.out.println("If you want to update the phone number, enter 4");
        System.out.println("If you want to update the salary, enter 5");

        String line = reader.readLine();
        switch (line) {
            case "1" -> {
                System.out.println("Please enter new name");
                employee.setFirstName(reader.readLine());
            }
            case "2" -> {
                System.out.println("Please enter new surname");
                employee.setSurName(reader.readLine());
            }
            case "3" -> {
                System.out.println("Please enter new age");
                try {
                    employee.setAge(Integer.parseInt(reader.readLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Is not a number");
                }
            }
            case "4" -> {
                System.out.println("Please enter new phone number");
                employee.setPhoneNumber(reader.readLine());
            }
            case "5" -> {
                System.out.println("Please enter new salary");
                try {
                    employee.setSalary(Integer.parseInt(reader.readLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Is not a number");
                }
            }
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to create an employee, please enter 1");
        System.out.println("If you want to find the employee, please enter 2");
        System.out.println("If you want to find all employees, please enter 3");
        System.out.println("If you want to update the employee, please enter 4");
        System.out.println("If you want to remove the employee, please enter 5");
        System.out.println("If you want to close the application, please enter 6");
        System.out.println();
    }

    private void deleteEmployee(BufferedReader reader) throws Exception {
        System.out.println("Please enter the phone number");
        EmployeeStorage.removeEmployee(reader.readLine());
    }

    private void findEmployee(BufferedReader reader) throws Exception {
        System.out.println("Please enter the phone number below");
        String number = reader.readLine();
        Employee employee = EmployeeStorage.getByPhoneNUmber(number);
        if (employee == null) {
            System.out.println("There is no such number in the database");
        } else {
            System.out.println(employee);
        }
    }

    private void findAll() {
        System.out.println("All employees:");
        Employee[] array = EmployeeStorage.getEmployees();
        for (int i = 0; i < EmployeeStorage.getNotes(); i++) {
            System.out.println(array[i]);
        }
    }

    private void createEmployee(BufferedReader reader) throws Exception {
        Employee employee = new Employee();

        System.out.println("Please enter the first name");
        String name = reader.readLine();
        employee.setFirstName(name);

        System.out.println("Please enter the surname");
        String surname = reader.readLine();
        employee.setSurName(surname);

        System.out.println("Please enter the age");
        int age = 0;
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Is not a number");
        }
        employee.setAge(age);

        System.out.println("Please enter the phone number");
        String number = reader.readLine();
        employee.setPhoneNumber(number);

        System.out.println("Please enter the salary");
        int salary = 0;
        try {
            salary = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Is not a number");
        }
        employee.setSalary(salary);

        EmployeeStorage.addEmployee(employee);
    }

    private void stop() {
        System.exit(0);
    }
}
