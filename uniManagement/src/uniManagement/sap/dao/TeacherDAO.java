package uniManagement.sap.dao;

import java.util.List;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;
import uniManagement.sap.model.Teacher;


public interface TeacherDAO {
	public int createTeacher(Teacher i_teacher);
	public int updateTeacher(Teacher i_teacher);
	public Teacher getTeacher(int id);
	public int deleteTeacher(int id);
	public int getNextTeacherID();
	public String getTeacherTitle(int id);
	public List<Teacher> getTeacherList();
	public List<Course> getTeacherCourses(int id);
	public List<Student> getTeacherStudents(int id);
	public int getNumberOfStudents(int courseID);
	public int getNumberOfCourses(int teacherID);
	public int getTitleIDByName(String titleName);
	public List<Teacher> getTop3();

}
