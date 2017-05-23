package com.websystique.springboot.service;

import java.util.List;

import com.websystique.springboot.model.Student;

public interface StudentService {
	Student findById(int id);

	Student findByName(String name);

	void saveStudent(Student student);

	void updateStudent(Student student);

	void deleteStudentById(int id);

	void deleteAllStudents();

	List<Student> findAllStudents();

	boolean isStudentExist(Student student);
        
        List<Student> findAllStudentsByCourse(int courseId);
        List<Student> morethanTwoCourses();
}
