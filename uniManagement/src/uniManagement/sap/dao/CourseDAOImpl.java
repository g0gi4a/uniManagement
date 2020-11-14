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
import uniManagement.sap.model.Teacher;

public class CourseDAOImpl implements CourseDAO {
	private JdbcTemplate jdbcTemplate;

	public CourseDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int createCourse(Course i_course, int teacherID) {
		int nextID = getNextCourseID();
		String sql = "INSERT INTO courses (courseID, courseName, courseCredit) VALUES (?,?,?)";

		int result = jdbcTemplate.update(sql, nextID, i_course.getCourseName(), i_course.getCourseCredit());
		if(result > 0) {
			try {
				createTeacherCurriculum(teacherID, nextID);
			} catch (DataIntegrityViolationException e) {
			    System.out.println("history already exist");
			} return result;
		}else {
			return result;
		}
	}

	@Override
	public int updateCourse(Course i_course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Course getCourse(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCourse(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNextCourseID() {
		String sql = "SELECT MAX(courseID) as courseID FROM courses";

		ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				int id = 0;
				if (rs.next()) {
					id = rs.getInt("courseID");
					return id;
				}
				return 0;
			}
		};
		return jdbcTemplate.query(sql, extractor) + 1;
	}

	@Override
	public int getNumberOfStudents(int courseID) {
		String sql = "SELECT COUNT(studentID) as numOfStudents\r\n" + "FROM studentcurriculum\r\n"
				+ "WHERE courseID = ?";
		int numberOfStudents = 0;

		try {
			numberOfStudents = jdbcTemplate.queryForObject(sql, new Object[] { courseID },
					(rs, rowNum) -> rs.getInt("numOfStudents"));
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

		return numberOfStudents;
	}

	public List<Course> getCourseList() {
		String sql = "SELECT * FROM courses";

		RowMapper<Course> rowMapper = new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("courseID");
				String name = rs.getString("courseName");
				int credit = rs.getInt("courseCredit");
				List<Teacher> taughtBy = getTaughtBy(id);
				int numOfStudents = getNumberOfStudents(id);
				return new Course(id, name, credit, numOfStudents, taughtBy);
			}

		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Course> getTop3Courses() {
		String sql = "SELECT\r\n" + "    c.courseID, c.courseName, c.courseCredit,\r\n"
				+ "    count(sc.studentID) AS 'numOfStudents'\r\n" + "FROM\r\n"
				+ "    studentcurriculum as sc INNER JOIN courses as c\r\n" + "    ON sc.courseID = c.courseID\r\n"
				+ "GROUP BY\r\n" + "    c.courseName\r\n" + "ORDER BY\r\n" + "    'numOfStudents' DESC\r\n"
				+ "LIMIT 3;";

		RowMapper<Course> rowMapper = new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("courseID");
				String name = rs.getString("courseName");
				int credit = rs.getInt("courseCredit");
				int totalStudents = rs.getInt("numOfStudents");

				return new Course(id, name, credit, totalStudents);
			}

		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Teacher> getTaughtBy(int id) {
		String sql = "SELECT t.teacherID, t.teacherFirstName, t.teacherLastName, tt.titleName\r\n"
				+ "FROM teachers as t INNER JOIN titles as tt on t.titleID = tt.titleID\r\n"
				+ "INNER JOIN teachercurriculum as tc on t.teacherID = tc.teacherID\r\n" + "WHERE tc.courseID = " + id;

		RowMapper<Teacher> rowMapper = new RowMapper<Teacher>() {

			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("teacherID");
				String fname = rs.getString("teacherFirstName");
				String lname = rs.getString("teacherLastName");
				String title = rs.getString("titleName");
				return new Teacher(id, fname, lname, title);
			}

		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public int createTeacherCurriculum(int teacherID, int courseID) {
		String sql = "INSERT INTO teachercurriculum (teacherID, courseID) VALUES (?,?)";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, teacherID, courseID);
		} catch (DataIntegrityViolationException e) { // catch if record already exits
			System.out.println("already exists");
			result = -1;
		}
		return result;
	}

	@Override
	public int deleteTeacherCurriculum(int teacherID, int courseID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
