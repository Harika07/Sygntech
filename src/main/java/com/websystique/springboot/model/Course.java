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
@Table(name = "Course_Table")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @NotEmpty
    @Column(name = "COURSE_NAME", nullable = false)
    private String name;
//    @ManyToMany(mappedBy = "courses")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   	@JoinTable(name = "student_course",  joinColumns = {
   			@JoinColumn(name = "courseId", nullable = false, updatable = false) },
   			inverseJoinColumns = { @JoinColumn(name = "studentId",
   					nullable = false, updatable = false) })
    private Set<Student> students;

    public Course() {
    }

    public Course(String cName) {
        this.name = cName;
    }

    public Course(String cName, Set<Student> students) {
        this.name = cName;
        this.students = students;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}
