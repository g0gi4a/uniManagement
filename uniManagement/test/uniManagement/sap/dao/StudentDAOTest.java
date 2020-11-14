package uniManagement.sap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;

class StudentDAOTest {
	private StudentDAO dao;

	@BeforeEach
	void setup() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false"); //&useSSL=false
		dataSource.setUsername("Paladinsta");
		dataSource.setPassword("asdasd");

		dao = new StudentDAOImpl(dataSource);
	}

	@Test
	void testCreateStudent() {
		Student test = new Student("Daniel", "Daskalov");
		int result = dao.createStudent(test);

		assertTrue(result > 0);
	}

	@Test
	void testUpdateStudent() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStudent() {
		//return student object with courses enrolled
		Student s1 = dao.getStudent(2);
		System.out.println("Student id: " + s1.getStudentID() + 
				"\nStudent f name: " + s1.getFirstName() + 
				"\nStudent l name: " + s1.getLastName());
		List<Course> courses = s1.getCourses();
		for (Course c : courses) {
			System.out.println("Course id: " + c.getCourseID() + 
					"\nCourse name: " + c.getCourseName() +
					"\nCourse credit: " + c.getCourseCredit());
		}
	}

	@Test
	void testDeleteStudent() {
		int id = 9;
		int result = dao.deleteStudent(id);
		
		assertTrue(result > 0);
	}

	@Test
	void testGetNextStudentID() {
		int nextID = dao.getNextStudentID();
		System.out.println(nextID);
		assertTrue(nextID > 0);
	}
	
	@Test
	void testGetStudentList() {
		List<Student> students = dao.getStudentList();
		for (Student s : students) {
			System.out.println(s.getCourses());
			System.out.println(s.getFirstName() + s.getLastName() + s.getStudentID());
		}
		assertTrue(!students.isEmpty());
	}
	
	@Test
	void testGetCourses() {
		//get the list of courses for studentID = 2
		List<Course> courses = dao.getCourses(2);
		for (Course c : courses) {
			System.out.println(c.getCourseID() + c.getCourseName() + c.getCourseCredit());
		}
		assertTrue(!courses.isEmpty());
	}
	
	@Test
	void testCreteStudentCirriculum() {
		//returns 0 if row already exits with given id pairs (only once we can have given student to a given course)
		int result = dao.creteStudentCurriculum(14, 1);
		assertTrue(result > 0);
	}

}
