package com.websystique.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springboot.model.Student;
import com.websystique.springboot.repositories.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



@Service("studentService")
@Transactional
public class StudentServiceImpl  implements StudentService {
        @PersistenceContext
        private EntityManager entityManager;
	@Autowired
	private StudentRepository studentRepository;
	
        @Override
	public Student findById(int id) {
		return studentRepository.findOne(id);
	}

        @Override
	public Student findByName(String name) {
		return studentRepository.findByName(name);
	}

        @Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

        @Override
	public void updateStudent(Student student){
		saveStudent(student);
	}

        @Override
	public void deleteStudentById(int id){
		studentRepository.delete(id);
	}

        @Override
	public void deleteAllStudents(){
		studentRepository.deleteAll();
	}

        @Override
	public List<Student> findAllStudents(){
		return studentRepository.findAll();
	}

        @Override
	public boolean isStudentExist(Student student) {
		return findByName(student.getName()) != null;
	}

    @Override
    public List<Student> findAllStudentsByCourse(int courseId) {
//      Query qery =entityManager.createQuery("select st.studentId,st.STUDENT_NAME,st.STUDENT_ADDRESS from student_table st join student_course sc  on st.studentId = sc.StudentId where sc.CourseId = :courseId");
//      qery.setParameter("courseId", courseId);
    	 TypedQuery<Student> qery =entityManager.createQuery("select studentId from student_table st join student_course sc  on st.studentId = sc.StudentId where sc.CourseId ="+courseId,Student.class);
//         qery.setParameter("courseId", courseId);
      return qery.getResultList();
    }
    
    @Override
    public List<Student> morethanTwoCourses() {
      Query qery = entityManager.createQuery("select st from student_table st  where st.studentId in ( select StudentId from student_course group by StudentId having count(*) > 1)");
      return qery.getResultList();
    }

}
