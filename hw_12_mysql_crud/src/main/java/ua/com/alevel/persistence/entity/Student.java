package ua.com.alevel.persistence.entity;

import java.sql.Timestamp;

public class Student extends BaseEntity {
    private String name;
    private String lastName;
    private int age;
    private Timestamp createdTime;

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id ='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", createdTime=" + createdTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}
