package uniManagement.sap.model;

import java.util.List;

public class Teacher {
	private int teacherID;
	private String teacherFirstName;
	private String teacherLastName;
	private String teacherTitle;
	private int titleID;
	private int numberOfCourses;
	private List<Course> courses;
	private int totalStudents;
	
	public Teacher() {
		
	}
	
	public Teacher(int i_id, String i_fname, String i_lname, int i_titleID, String i_titleName, List<Course> i_courses, int i_numOfCourses) {
		this.teacherID 			= i_id;
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.titleID			= i_titleID;
		this.teacherTitle		= i_titleName;
		this.courses 			= i_courses;
		this.numberOfCourses    = i_numOfCourses;
	}
	
	public Teacher(int i_id, String i_fname, String i_lname, int i_titleID, String i_titleName, int i_totalStudents) {
		this.teacherID 			= i_id;
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.titleID			= i_titleID;
		this.teacherTitle		= i_titleName;
		this.totalStudents 		= i_totalStudents;
	}
	
	public Teacher(int i_id, String i_fname, String i_lname, int i_titleID) {
		this.teacherID 			= i_id;
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.titleID			= i_titleID;
	}
	
	public Teacher(int i_id, String i_fname, String i_lname, String i_titleName) {
		this.teacherID 			= i_id;
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.teacherTitle		= i_titleName;
	}
	
	
	public Teacher(String i_fname, String i_lname, int i_titleID, String i_title) {
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.titleID			= i_titleID;
		this.teacherTitle		= i_title;
	}
	
	public Teacher(String i_fname, String i_lname, int i_titleID) {
		this.teacherFirstName 	= i_fname;
		this.teacherLastName 	= i_lname;
		this.titleID			= i_titleID;
	}
	
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherFirstName() {
		return teacherFirstName;
	}
	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}
	public String getTeacherLastName() {
		return teacherLastName;
	}
	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
	public String getTeacherTitle() {
		return teacherTitle;
	}
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public int getNumberOfCourses() {
		return numberOfCourses;
	}

	public void setNumberOfCourses(int numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}

	public int getTitleID() {
		return titleID;
	}

	public void setTitleID(int titleID) {
		this.titleID = titleID;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getTotalStudents() {
		return totalStudents;
	}

	public void setNumOfStudents(int i_totalStudents) {
		this.totalStudents = i_totalStudents;
	}
}
