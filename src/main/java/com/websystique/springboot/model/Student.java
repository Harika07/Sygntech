package com.websystique.springboot.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "student_table")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @NotEmpty
    @Column(name = "STUDENT_NAME", nullable = false)
    private String name;
    @Column(name = "STUDENT_ADDRESS", nullable = false)
    private String studentAddress;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Course.class)
//    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "StudentId", referencedColumnName = "studentId"), inverseJoinColumns = @JoinColumn(name = "CourseId", referencedColumnName = "courseId"))
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private Set<Course> courses;

    public Student() {
    }

    public Student(String sName) {
        this.name = sName;
    }

    public Student(String sName, Set<Course> courses) {
        this.name = sName;
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
	
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
