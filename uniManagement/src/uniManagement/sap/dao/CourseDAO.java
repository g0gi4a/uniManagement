package uniManagement.sap.dao;


import java.util.List;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Teacher;

public interface CourseDAO {
	public int createCourse(Course i_course, int teacherID);
	public int updateCourse(Course i_course);
	public Course getCourse(int id);
	public int deleteCourse(int id);
	public int getNextCourseID();
	public int getNumberOfStudents(int id);
	public List<Course> getCourseList();
	public List<Course> getTop3Courses();
	public List<Teacher> getTaughtBy(int id);
	public int createTeacherCurriculum(int teacherID, int courseID);
	public int deleteTeacherCurriculum(int teacherID, int courseID);

}
