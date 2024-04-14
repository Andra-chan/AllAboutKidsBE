package com.allaboutkids.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "teacher", nullable = false)
    private String teacher;

    @Column(name = "course", nullable = false)
    private String course;

    @Column(name = "day_of_class", nullable = false)
    private String dayOfClass;

    @Column(name = "time_of_class", nullable = false)
    private String timeOfClass;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public Student() {

    }

    public Student(Long id, String firstName, String lastName, String dateOfBirth, String teacher, String course, String dayOfClass, String timeOfClass, Parent parent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.teacher = teacher;
        this.course = course;
        this.dayOfClass = dayOfClass;
        this.timeOfClass = timeOfClass;
        this.parent = parent;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDayOfClass() {
        return dayOfClass;
    }

    public Parent getParent() {
        return parent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setDayOfClass(String dayOfClass) {
        this.dayOfClass = dayOfClass;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTimeOfClass() {
        return timeOfClass;
    }

    public void setTimeOfClass(String timeOfClass) {
        this.timeOfClass = timeOfClass;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", teacher='" + teacher + '\'' +
                ", course='" + course + '\'' +
                ", dayOfClass='" + dayOfClass + '\'' +
                ", timeOfClass='" + timeOfClass + '\'' +
                ", parent=" + parent +
                '}';
    }

    // getters and setters
}