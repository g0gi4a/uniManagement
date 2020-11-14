package uniManagement.sap.dao;

import java.util.List;

import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;

public interface StudentDAO {
	public int createStudent(Student i_student); //adding student to the system
	public int createStudent(Student i_student, String i_courseName); //adding student to the system + course
	public int addCourseToStudent(Student i_student, String i_courseName); //adding course to a given student 
	public int removeCourseFromStudent(Student i_student, String i_courseName);
	public int updateStudent(Student student);
	public Student getStudent(int id);
	public int deleteStudent(int id);
	public int getNextStudentID();
	public List<Student> getStudentList();
	public List<Course> getCourses(int id);
	public int getTotalCredit(int id);
	public int creteStudentCurriculum(int studentID, int courseID);
	public int deleteStudentCurriculum(int studentID, int courseID);
	public Course getCourseByName(String courseName);
}
