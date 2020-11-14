package uniManagement.sap.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uniManagement.sap.dao.StudentDAO;
import uniManagement.sap.model.Course;
import uniManagement.sap.model.Student;

@Controller
public class StudentController {
	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "/students")
	public ModelAndView listStudents(ModelAndView model) {
		List<Student> studentsList = studentDAO.getStudentList();
		model.addObject("studentsList", studentsList);
		model.setViewName("students");
		return model;
	}

	@RequestMapping(value = "/students/credit", method = RequestMethod.GET)
	public ModelAndView listStudentsCredit(ModelAndView model) {
		List<Student> studentsList = studentDAO.getStudentList();
		model.addObject("studentsList", studentsList);
		model.setViewName("studentsCredit");
		return model;
	}

	@RequestMapping(value = "/students/new", method = RequestMethod.GET)
	public ModelAndView newStudent(ModelAndView model) {
		Student newStudent = new Student();
		model.addObject("student", newStudent);
		model.setViewName("addStudent");
		return model;
	}

	@RequestMapping(value = "/students/save", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Student student, @RequestParam Map<String, String> allParams) {
		// depending on input parameters determine whether to create a student or
		// student + course;
		System.out.println("Parameters are " + allParams.entrySet());
		String course = allParams.get("courses");
		if (course == "") {
			studentDAO.createStudent(student);
		} else {
			studentDAO.createStudent(student, course);
		}
		return new ModelAndView("redirect:/students");
	}

	@RequestMapping(value = "/students/manage", method = RequestMethod.GET)
	public ModelAndView manageStudent(ModelAndView model) {
		Student student = new Student();
		Course course = new Course();
		model.addObject("student", student);
		model.addObject("course", course);
		model.setViewName("manageStudent");
		return model;
	}

	@RequestMapping(value = "/students/update", method = RequestMethod.POST)
	public ModelAndView addCourse(@ModelAttribute Student student, @RequestParam Map<String, String> allParams) {
		System.out.println("Parameters are" + allParams.entrySet());
		String id = allParams.get("studentID");
		Student s1 = studentDAO.getStudent(Integer.parseInt(id));
		String courseName = allParams.get("courses");
		int result = 0;
		
		String action = allParams.get("action");
		System.out.println(action);
		if(action.equals("add")) {
			System.out.println("adding");
			result = studentDAO.addCourseToStudent(s1, courseName);
			System.out.println(result);
			if(result == -1) {
				//pop message for already exists to be implemented
			}
		}else if(action.equals("delete")) {
			System.out.println("deleting");
			studentDAO.removeCourseFromStudent(s1, courseName);
		}
		
		return new ModelAndView("redirect:/students");

		// add error handling for
		// 1) not existing student
		// 2) not existing course
		// 3) already existing entry for given student and course

	}
}
