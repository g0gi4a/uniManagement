package uniManagement.sap.model;

import java.util.List;

public class Student {
	private int 				studentID;
	private String 				studentFirstName;
	private String 				studentLastName;
	private List<Course> 		studentCourses;
	private int					studentCredit;
	
	public Student() {
	}

	public Student(int i_studentID, String i_firstName, String i_lastName, List<Course> i_studentCourses, int i_credit) {
		this.studentID = i_studentID;
		this.studentFirstName = i_firstName;
		this.studentLastName = i_lastName;
		this.studentCourses = i_studentCourses;
		this.studentCredit = i_credit;
	}
	
	public Student(int i_studentID, String i_firstName, String i_lastName, List<Course> i_studentCourses) {
		this.studentID = i_studentID;
		this.studentFirstName = i_firstName;
		this.studentLastName = i_lastName;
		this.studentCourses = i_studentCourses;
	}
	
	public Student(int i_studentID, String i_firstName, String i_lastName) {
		this.studentID = i_studentID;
		this.studentFirstName = i_firstName;
		this.studentLastName = i_lastName;
	}
	
	public Student(String i_firstName, String i_lastName) {
		this.studentFirstName = i_firstName;
		this.studentLastName = i_lastName;
	}
	
	
	public Student(String i_firstName, String i_lastName, List<Course> i_studentCourses) {
		this.studentFirstName = i_firstName;
		this.studentLastName = i_lastName;
		this.studentCourses = i_studentCourses;
	}

	// get id
	public int getStudentID() {
		return studentID;
	}

	// set first name
	public void setFirstName(String i_firstName) {
		this.studentFirstName = i_firstName;
	}

	// get first name
	public String getFirstName() {
		return studentFirstName;
	}

	// set last name
	public void setLastName(String i_lastName) {
		this.studentLastName = i_lastName;
	}

	// get last name
	public String getLastName() {
		return studentLastName;
	}
	
	// set course
	public void addCourse(Course i_course){
		this.studentCourses.add(i_course);
	}
	
	// get courses
	public List<Course> getCourses() {
		return studentCourses;
	}

	public int getStudentCredit() {
		return studentCredit;
	}

	public void setStudentCredit(int studentCredit) {
		this.studentCredit = studentCredit;
	}
}
