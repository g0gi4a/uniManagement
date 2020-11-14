package uniManagement.sap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import uniManagement.sap.model.Course;

class CourseDAOTest {
	private CourseDAO dao;
	@BeforeEach
	void setup() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false"); //&useSSL=false
		dataSource.setUsername("Paladinsta");
		dataSource.setPassword("asdasd");

		dao = new CourseDAOImpl(dataSource);
	}

	@Test
	void testCreateCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNextCourseID() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNumberOfStudents() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCourseList() {
		List<Course> courses = dao.getCourseList();
		for (Course c : courses) {
			System.out.println(c.getCourseID() + c.getCourseName() + c.getCourseCredit() + c.getCourseTeachers());
		}
		assertTrue(!courses.isEmpty());
	}
	
	@Test
	void testGetTop3Courses() {
		List<Course> courses = dao.getTop3Courses();
		for (Course c : courses) {
			System.out.println(c.getCourseID() + " " + c.getCourseName() + " " + c.getCourseCredit() + " " + c.getTotalStudents());
		}
		assertTrue(!courses.isEmpty());
	}

}
