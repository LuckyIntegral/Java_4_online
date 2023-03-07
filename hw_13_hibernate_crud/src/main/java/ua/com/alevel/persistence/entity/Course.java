package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import ua.com.alevel.persistence.type.CourseType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "course_type", nullable = false)
    private CourseType type;
    @ManyToMany(mappedBy = "courseSet", cascade = CascadeType.ALL)
    private Set<Student> studentSet;

    public Course() {
        super();
        studentSet = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", created=" + getCreated() +
                ", updated=" + getUpdated() +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }
}
