package com.websystique.springboot.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websystique.springboot.model.Course;
import com.websystique.springboot.model.Student;
import com.websystique.springboot.repositories.CourseRepository;
import com.websystique.springboot.service.CourseServiceImpl;



@Controller
public class AppController{
    @RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

//	@Autowired
////        @Qualifier("course")
//	private CourseServiceImpl courseServiceImpl;
//	@RequestMapping("/")
//	String home(ModelMap modal) {
//		
//		final Student student1 = new Student("student1");
//		final Student student2 = new Student("student2");
//		final Student student3 = new Student("student3");
////		courseRepository.saveCourse(
////				new HashSet<Course>(){{
////						add(new Course("Java101",new HashSet<Student>(){{
////			add(student1);
////			add(student2);
////			}}));
////						add(new Course("CA123",new HashSet<Student>(){{
////							add(student1);
////							add(student3);
////							}}));
////		}});
//		courseServiceImpl.saveCourse(new Course("CA123",new HashSet<Student>(){{
//							add(student1);
//							add(student3);
//							}}));
//		modal.addAttribute("title","CRUD Example");
//		return "index";
//	}

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}
}
