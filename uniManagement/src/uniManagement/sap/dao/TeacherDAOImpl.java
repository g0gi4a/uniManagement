package uniManagement.sap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;
import uniManagement.sap.model.Teacher;

public class TeacherDAOImpl implements TeacherDAO {
	private JdbcTemplate jdbcTemplate;

	public TeacherDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int createTeacher(Teacher i_teacher) {
		int nextID = getNextTeacherID();
		int titleID = getTitleIDByName(i_teacher.getTeacherTitle());
		String sql = "INSERT INTO teachers (teacherID, teacherFirstName, teacherLastName, titleID) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql, nextID, i_teacher.getTeacherFirstName(), i_teacher.getTeacherLastName(), titleID);
	}

	@Override
	public int updateTeacher(Teacher i_teacher) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Teacher getTeacher(int id) {
		String sql = "SELECT * FROM teachers WHERE teacherID = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
        new Teacher(
                rs.getInt("teacherID"),
                rs.getString("teacherFirstName"),
                rs.getString("teacherLastName"),
                rs.getInt("titleID")
        ));
	}

	@Override
	public int deleteTeacher(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNextTeacherID() {
		String sql = "SELECT * FROM teachers WHERE teacherID = ( SELECT MAX(teacherID) FROM teachers)";

		ResultSetExtractor<Teacher> extractor = new ResultSetExtractor<Teacher>() {

			@Override
			public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int id = rs.getInt("teacherID");
					String fname = rs.getString("teacherFirstName");
					String lname = rs.getString("teacherLastName");
					int titleID = rs.getInt("titleID");

					return new Teacher(id, fname, lname, titleID);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor).getTeacherID() + 1;
	}

	@Override
	public List<Teacher> getTeacherList() {
		String sql = "SELECT * FROM teachers";

		RowMapper<Teacher> rowMapper = new RowMapper<Teacher>() {

			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("teacherID");
				String fname = rs.getString("teacherFirstName");
				String lname = rs.getString("teacherLastName");
				int titleID = rs.getInt("titleID");
				//get courses
				List<Course> courses = getTeacherCourses(id);
				//get title
				String titleName = getTeacherTitle(titleID);
		
				return new Teacher(id, fname, lname, titleID, titleName, courses, courses.size());
			}
		};

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Course> getTeacherCourses(int id) {
		String sql = "SELECT c.courseID, c.courseName, c.courseCredit\r\n" + "FROM teachers as t\r\n"
				+ "INNER JOIN teachercurriculum as tc\r\n" + "    ON t.teacherID = tc.teacherID\r\n"
				+ "INNER JOIN courses as c\r\n" + "    ON tc.courseID = c.courseID\r\n" + "    WHERE t.teacherID = "
				+ id;

		RowMapper<Course> rowMapper = new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("courseID");
				String courseName = rs.getString("courseName");
				int courseCredit = rs.getInt("courseCredit");
				int numOfStudents = getNumberOfStudents(id);
				return new Course(id, courseName, courseCredit, numOfStudents);
			}

		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Student> getTeacherStudents(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTeacherTitle(int id) {
		String sql = "SELECT titleName FROM titles WHERE titleID = ?";
		String title = null;
		
		try {
			title = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
	        rs.getString("titleName"));
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		return title;
	}
	
	@Override
	public int getNumberOfStudents(int courseID) {
		String sql = "SELECT COUNT(studentID) as numOfStudents\r\n"
				+ "FROM studentcurriculum\r\n"
				+ "WHERE courseID = ?";
		int numberOfStudents = 0;
		
		try {
			numberOfStudents = jdbcTemplate.queryForObject(sql, new Object[]{courseID}, (rs, rowNum) ->
	        rs.getInt("numOfStudents"));
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
		
		return numberOfStudents;
	}
	
	@Override
	public int getNumberOfCourses(int teacherID) {
		String sql = "SELECT COUNT(courseID) as numOfCourses\r\n"
				+ "FROM teachercurriculum\r\n"
				+ "WHERE teacherID = ?";
		int numberOfCourses = 0;
		
		try {
			numberOfCourses = jdbcTemplate.queryForObject(sql, new Object[]{teacherID}, (rs, rowNum) ->
	        rs.getInt("numOfCourses"));
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
		
		return numberOfCourses;
	}

	@Override
	public int getTitleIDByName(String titleName) {
		String sql = "SELECT titleID \r\n"
				+ "FROM titles\r\n"
				+ "WHERE titleName = ?";
		int titleID = 0;
		
		try {
			titleID = jdbcTemplate.queryForObject(sql, new Object[]{titleName}, (rs, rowNum) ->
	        rs.getInt("titleID"));
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
		
		return titleID;
	}

	@Override
	public List<Teacher> getTop3() {
		String sql = "SELECT t.teacherID, t.teacherFirstName, t.teacherLastName, t.titleID, COUNT(tc.courseID) as 'num'\r\n"
				+ "FROM teachers as t INNER JOIN teachercurriculum as tc ON t.teacherID = tc.teacherID\r\n"
				+ "INNER JOIN studentcurriculum as sc ON tc.courseID = sc.courseID\r\n"
				+ "GROUP BY t.teacherID ORDER BY num DESC LIMIT 3";

		RowMapper<Teacher> rowMapper = new RowMapper<Teacher>() {

			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("teacherID");
				String fname = rs.getString("teacherFirstName");
				String lname = rs.getString("teacherLastName");
				int titleID = rs.getInt("titleID");
				int totalStudents = rs.getInt("num");
				//get title
				String titleName = getTeacherTitle(titleID);
		
				return new Teacher(id, fname, lname, titleID, titleName, totalStudents);
			}
		};

		return jdbcTemplate.query(sql, rowMapper);
	}
}
