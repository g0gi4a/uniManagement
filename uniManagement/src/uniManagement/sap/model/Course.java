package uniManagement.sap.model;

import java.util.List;

public class Course {
	private int courseID;
	private 	String courseName;
	private int courseCredit;
	private int totalStudents;
	private int currentTeacherID;
	private List<Teacher> courseTeachers;
	
	public Course() {}
	
	public Course(int i_courseID, String i_courseName, int i_courseCredit, int i_numOfStudents, List<Teacher> i_courseTeachers) {
		this.courseID			= i_courseID;
		this.courseName 		= i_courseName;
		this.courseCredit 		= i_courseCredit;
		this.totalStudents  	= i_numOfStudents;
		this.courseTeachers     = i_courseTeachers;
	}
	
	public Course(int i_courseID, String i_courseName, int i_courseCredit, int i_numOfStudents) {
		this.courseID		= i_courseID;
		this.courseName 	= i_courseName;
		this.courseCredit 	= i_courseCredit;
		this.totalStudents  = i_numOfStudents;
	}
	
	public Course(int i_courseID, String i_courseName, int i_courseCredit) {
		this.courseID		= i_courseID;
		this.courseName 	= i_courseName;
		this.courseCredit 	= i_courseCredit;
	}
	
	public Course(String i_courseName, int i_courseCredit) {
		this.courseName 	= i_courseName;
		this.courseCredit 	= i_courseCredit;
	}

	public int getCourseID() {
		return courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}

	public List<Teacher> getCourseTeachers() {
		return courseTeachers;
	}

	public void setCourseTeachers(List<Teacher> courseTeachers) {
		this.courseTeachers = courseTeachers;
	}

	public int getCurrentTeacherID() {
		return currentTeacherID;
	}

	public void setCurrentTeacherID(int currentTeacherID) {
		this.currentTeacherID = currentTeacherID;
	}

}
