package com.websystique.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
Course findByName(String courseName);		
}
