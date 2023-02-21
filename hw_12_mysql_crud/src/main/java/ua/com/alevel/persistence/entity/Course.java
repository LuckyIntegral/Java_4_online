package ua.com.alevel.persistence.entity;

import java.sql.Timestamp;

public class Course extends BaseEntity {
    private String name;
    private String subject;
    private Timestamp createdTime;

    public Course() {
        super();
    }


    @Override
    public String toString() {
        return "Course{" +
                "id ='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
