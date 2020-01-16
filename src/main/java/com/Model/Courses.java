package com.Model;

import javax.persistence.*;

@Entity
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column
    private String course_name;

    @Column
    private String Course_title;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "Course_rep_ID")
    private Student student;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_title() {
        return Course_title;
    }

    public void setCourse_title(String course_title) {
        Course_title = course_title;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "ID=" + ID +
                ", course_name='" + course_name + '\'' +
                ", Course_title='" + Course_title + '\'' +
                '}';
    }
}
