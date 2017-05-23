package com.websystique.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springboot.model.Course;
import com.websystique.springboot.repositories.CourseRepository;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findById(int id) {
        return courseRepository.findOne(id);
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);

    }

    @Override
    public void updateCourse(Course course) {
        saveCourse(course);

    }

    @Override
    public void deleteCourseById(int id) {
        courseRepository.delete(id);
    }

    @Override
    public void deleteAllCourses() {
        courseRepository.deleteAll();

    }

    @Override
    public List<Course> findAllCourses() {

        return courseRepository.findAll();
    }

    @Override
    public boolean isCourseExist(Course course) {

        return findByName(course.getName()) != null;
    }

}
