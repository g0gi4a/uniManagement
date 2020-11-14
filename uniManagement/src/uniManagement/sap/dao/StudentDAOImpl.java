package uniManagement.sap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;

public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;

	public StudentDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	// create student
	public int createStudent(Student i_student) {
		int nextID = getNextStudentID();
		String sql = "INSERT INTO students (studentID, studentFirstName, studentLastName) VALUES (?,?,?)";
		return jdbcTemplate.update(sql, nextID, i_student.getFirstName(), i_student.getLastName());
	}

	@Override
	// create student with course
	public int createStudent(Student i_student, String i_courseName) {
		int nextID = getNextStudentID();
		String sql = "INSERT INTO students (studentID, studentFirstName, studentLastName) VALUES (?,?,?)";
		Course course = getCourseByName(i_courseName);
		int courseID = course.getCourseID();
		if (courseID != 0) {
			int result = jdbcTemplate.update(sql, nextID, i_student.getFirstName(), i_student.getLastName());
			if (result > 0) {
				// add course to student curriculum if student was created
				result = creteStudentCurriculum(nextID, courseID);
			}
			return result;
		} else {
			return 0; // if course id not found do not create student and course
		}
	}

	@Override
	public int addCourseToStudent(Student i_student, String i_courseName) {
		Course course = getCourseByName(i_courseName);
		if (course != null) {
			int studentID = i_student.getStudentID();
			int courseID = course.getCourseID();
			return creteStudentCurriculum(studentID, courseID);
		}
		return -1;

	}

	@Override
	public int removeCourseFromStudent(Student i_student, String i_courseName) {
		Course course = getCourseByName(i_courseName);
		if (course != null) {
			return deleteStudentCurriculum(i_student.getStudentID(), course.getCourseID());
		}
		return -1;
	}

	@Override
	public int updateStudent(Student student) {
		String sql = "UPDATE Students SET studentFirstName = ?, studentLastName = ? WHERE studentID = ?";
		return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getStudentID());
	}

	@Override
	public Student getStudent(int id) {
		List<Course> studentCourses = getCourses(id);
		String sql = "SELECT * FROM students WHERE studentID = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> new Student(rs.getInt("studentID"),
				rs.getString("studentFirstName"), rs.getString("studentLastName"), studentCourses));
	}

	@Override
	public int deleteStudent(int id) {
		// delete only student table, cascade delete from studentcurriculum
		String sql = "DELETE FROM students WHERE studentID = " + id;
		int result = jdbcTemplate.update(sql);
		return result;
	}

	@Override
	public int getNextStudentID() {
		String sql = "SELECT MAX(studentID) as studentID FROM students";

		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int id = 0;
				if (rs.next()) {
					id = rs.getInt("studentID");
					return id;
				}
				return 0;
			}
		};
		return jdbcTemplate.query(sql, extractor) + 1;
	}

	public List<Student> getStudentList() {
		String sql = "SELECT * FROM students";

		RowMapper<Student> rowMapper = new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("studentID");
				String fname = rs.getString("studentFirstName");
				String lname = rs.getString("studentLastName");
				List<Course> courses = getCourses(id);
				int totalCredit = getTotalCredit(id);
				return new Student(id, fname, lname, courses, totalCredit);
			}

		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	public List<Course> getCourses(int id) {
		String sql = "SELECT c.courseID, c.courseName, c.courseCredit\r\n" + "FROM students as s\r\n"
				+ "INNER JOIN studentcurriculum as sc\r\n" + "    ON s.studentID = sc.studentID\r\n"
				+ "INNER JOIN courses as c\r\n" + "    ON sc.courseID = c.courseID\r\n" + "    WHERE s.studentID = "
				+ id;

		RowMapper<Course> rowMapper = new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("courseID");
				String courseName = rs.getString("courseName");
				int courseCredit = rs.getInt("courseCredit");
				return new Course(id, courseName, courseCredit);
			}

		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int getTotalCredit(int id) {
		int totalCredit = 0;
		Student s1 = getStudent(id);
		for (Course c : s1.getCourses()) {
			totalCredit = totalCredit + c.getCourseCredit();
		}
		return totalCredit;
	}

	@Override
	// create link student -> course in studentcurriculum table
	public int creteStudentCurriculum(int studentID, int courseID) {
		String sql = "INSERT INTO studentcurriculum (studentID, courseID) VALUES (?,?)";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, studentID, courseID);
		} catch (DataIntegrityViolationException e) { // catch if record already exits
			System.out.println("already exists");
			result = -1;
		}
		return result;
	}

	@Override
	// create link student -> course in studentcurriculum table
	public int deleteStudentCurriculum(int studentID, int courseID) {
		String sql = "DELETE FROM studentcurriculum WHERE studentID = ? AND courseID = ?";
		return jdbcTemplate.update(sql, studentID, courseID);
	}

	@Override
	public Course getCourseByName(String courseName) {
		String sql = "SELECT * FROM courses WHERE courseName = ?";
		Course course = null;
		try {
			course = jdbcTemplate.queryForObject(sql, new Object[] { courseName },
					(rs, rowNum) -> new Course(rs.getInt("courseID"), rs.getString("courseName"),
							rs.getInt("courseCredit")));
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Course not found");
		}
		return course;

	}

}
