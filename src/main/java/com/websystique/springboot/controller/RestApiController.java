package com.websystique.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Course;
import com.websystique.springboot.model.Student;
import com.websystique.springboot.service.CourseService;
import com.websystique.springboot.service.StudentService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	CourseService courseService; //Service which will do all data retrieval/manipulation work
	@Autowired
	StudentService studentService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Courses---------------------------------------------

	@RequestMapping(value = "/course/", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> listAllCourses() {
		List<Course> courses = courseService.findAllCourses();
		if (courses.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	// -------------------Retrieve Single Course------------------------------------------

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCourse(@PathVariable("id") int id) {
		logger.info("Fetching Course with id {}", id);
		Course course = courseService.findById(id);
		if (course == null) {
			logger.error("Course with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Course with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	// -------------------Create a Course-------------------------------------------

	@RequestMapping(value = "/course/", method = RequestMethod.POST)
	public ResponseEntity<?> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Course : {}", course);

		if (courseService.isCourseExist(course)) {
			logger.error("Unable to create. A Course with name {} already exist", course.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Course with name " + 
			course.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		courseService.saveCourse(course);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/course/{id}").buildAndExpand(course.getCourseId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

//	// ------------------- Update a Course ------------------------------------------------

	@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
		logger.info("Updating Course with id {}", id);

		Course currentCourse = courseService.findById(id);

		if (currentCourse == null) {
			logger.error("Unable to update. Course with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Course with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentCourse.setName(course.getName());
		currentCourse.setCourseId(course.getCourseId());

		courseService.updateCourse(currentCourse);
		return new ResponseEntity<Course>(currentCourse, HttpStatus.OK);
	}

//	// ------------------- Delete a Course-----------------------------------------

	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCourse(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Course with id {}", id);

		Course course = courseService.findById(id);
		if (course == null) {
			logger.error("Unable to delete. Course with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Course with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		courseService.deleteCourseById(id);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}

//	// ------------------- Delete All Courses-----------------------------

        @RequestMapping(value = "/course/", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteAllCourses() {
		logger.info("Deleting All Courses");

		courseService.deleteAllCourses();
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}

      // -------------------Retrieve All Students by course name ---------------------------------------------
        
        
        @RequestMapping(value = "/findAllStudentsByCourse/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> findAllStudentsByCourse(@PathVariable("name") String name) {
		logger.info("Fetching Course with name {}", name);
		Course course = courseService.findByName(name);
		if (course == null) {
			logger.error("Course with name {} not found.", name);
			return new ResponseEntity(new CustomErrorType("Course with name " + name 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
                List<Student> students = studentService.findAllStudentsByCourse(course.getCourseId());
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
        
        // -------------------Retrieve All Students with two courses---------------------------------------------
         @RequestMapping(value = "/morethanTwoCourses/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> morethanTwoCourses() {
		logger.info("Fetching morethanTwoCourses info {}" );
		
                List<Student> students = studentService.morethanTwoCourses();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
        
        
        	// -------------------Retrieve All Students---------------------------------------------

	@RequestMapping(value = "/student/", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> listAllStudents() {
		List<Student> student = studentService.findAllStudents();
		if (student.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
	}

	// -------------------Retrieve Single Student------------------------------------------

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
		logger.info("Fetching student with id {}", id);
		Student student = studentService.findById(id);
		if (student == null) {
			logger.error("Student with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Student with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	// -------------------Create a Student-------------------------------------------

	@RequestMapping(value = "/student/", method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Student : {}", student);

		if (studentService.isStudentExist(student)) {
			logger.error("Unable to create. A Course with name {} already exist", student.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Course with name " + 
			student.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		studentService.saveStudent(student);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getStudentId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

//	// ------------------- Update a student ------------------------------------------------

	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
		logger.info("Updating Student with id {}", id);

		Student currentStudent = studentService.findById(id);

		if (currentStudent == null) {
			logger.error("Unable to update. Course with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Course with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentStudent.setName(student.getName());
		currentStudent.setStudentId(student.getStudentId());
		currentStudent.setStudentAddress(student.getStudentAddress());

		studentService.updateStudent(currentStudent);
		return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
	}

//	// ------------------- Delete a student-----------------------------------------

	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting student with id {}", id);

		Student student = studentService.findById(id);
		if (student == null) {
			logger.error("Unable to delete. Student with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Student with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		studentService.deleteStudentById(id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}

//	// ------------------- Delete All Courses-----------------------------

        @RequestMapping(value = "/student/", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deleteAllStudents() {
		logger.info("Deleting All Students");

		studentService.deleteAllStudents();
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}

}