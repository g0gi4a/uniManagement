package uniManagement.sap.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import uniManagement.sap.model.Teacher;

class TeacherDAOTest {
	private TeacherDAO dao;

	@BeforeEach
	void setup() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false");
		dataSource.setUsername("Paladinsta");
		dataSource.setPassword("asdasd");

		dao = new TeacherDAOImpl(dataSource);
	}
	@Test
	void testCreateTeacher() {
		Teacher test = new Teacher("Hristo", "Petrov", 3);
		int result = dao.createTeacher(test);

		assertTrue(result > 0);
	}

	@Test
	void testUpdateTeacher() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeacher() {
		Teacher result = dao.getTeacher(2);
		System.out.println(result.getTeacherFirstName() + result.getTeacherLastName() + result.getTitleID());
		assertTrue(result != null);
	}

	@Test
	void testDeleteTeacher() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNextTeacherID() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeacherList() {
		List<Teacher> teachers = dao.getTeacherList();
		for (Teacher t : teachers) {
			System.out.println(t.getTeacherID() + t.getTeacherFirstName() + t.getTeacherLastName() + t.getTeacherTitle() + t.getCourses());
		}
		assertTrue(!teachers.isEmpty());
	}

	@Test
	void testGetTeacherCourses() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeacherStudents() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetTeacherTitle() {
		String title = dao.getTeacherTitle(2);
		System.out.println(title);
		
		assertTrue(title != null);
	}

}
