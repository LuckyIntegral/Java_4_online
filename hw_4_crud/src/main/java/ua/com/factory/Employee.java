package ua.com.factory;

public class Employee {
    private String firstName;
    private String surName;
    private int age;
    private String phoneNumber;
    private int salary;
    private static final String DEFAULT_VALUE = "not specified";

    public Employee() {}

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void setFirstName(String firstName) {
        if (firstName.matches("\\d")) {
            System.out.println("The name in incorrect, please check your name");
            this.firstName = DEFAULT_VALUE;
        } else {
            this.firstName = firstName;
        }
    }

    public void setSurName(String surName) {
        if (surName.matches("\\d")){
            System.out.println("The surname is incorrect, please check your surname");
            this.surName = DEFAULT_VALUE;
        }else {
            this.surName = surName;
        }
    }

    public void setAge(int age) {
        if (age > 100 || age <= 0) {
            System.out.println("The age is incorrect, please check your age");
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("([+\\-\\d ]+)")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("The phone number is incorrect, please write your phone number");
            this.phoneNumber = DEFAULT_VALUE;
        }
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            System.out.println("The salary is incorrect, please check your salary");
            this.salary = 0;
        } else {
            this.salary = salary;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSalary() {
        return salary;
    }
}
