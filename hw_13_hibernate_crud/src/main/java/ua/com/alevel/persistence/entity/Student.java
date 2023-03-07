package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column()
    private Integer age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stud_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "stud_id")
    )
    private Set<Course> courseSet;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", created=" + getCreated() +
                ", updated=" + getUpdated() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", courseSet=" + courseSet +
                '}';
    }

    public Student() {
        super();
        courseSet = new HashSet<>();
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
