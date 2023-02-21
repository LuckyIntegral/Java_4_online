package ua.com.factory;

public class EmployeeStorage {
    private static int notes;
    private static final int DEFAULT_CAPACITY = 10;
    private static int size = DEFAULT_CAPACITY;
    private static Employee[] data = new Employee[DEFAULT_CAPACITY];

    private EmployeeStorage() {}

    public static Employee[] getEmployees() {
        return data;
    }

    private static void increaseSize() {
        Employee[] newData = new Employee[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        size = newData.length;
        data = newData;
    }

    public static void addEmployee(Employee employee) {
        data[notes] = employee;
        notes++;
        if (notes == size) {
            increaseSize();
        }
    }

    public static Employee getByPhoneNUmber(String phoneNumber) {
        for (int i = 0; i < notes; i++) {
            if (data[i].getPhoneNumber().equals(phoneNumber)) {
                return data[i];
            }
        }
        return null;
    }

    public static void removeEmployee(String phoneNumber) {
        int index = -1;
        for (int i = 0; i < notes; i++) {
            if (data[i].getPhoneNumber().equals(phoneNumber)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("There is no such number in the database");
            return;
        }
        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
            data[i + 1] = null;
        }
        notes--;
    }

    public static int getNotes() {
        return notes;
    }
}
