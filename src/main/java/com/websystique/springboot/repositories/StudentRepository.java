package com.websystique.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
Student findByName(String studentName);		
}
