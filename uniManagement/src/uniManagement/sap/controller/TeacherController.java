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
import uniManagement.sap.dao.TeacherDAO;
import uniManagement.sap.model.Student;
import uniManagement.sap.model.Teacher;

@Controller
public class TeacherController {
	@Autowired
	private TeacherDAO teacherDAO;
	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping(value = "/teachersAndStudents")
	public ModelAndView listTeachersAndStudents(ModelAndView model) {
		List<Teacher> teachersList = teacherDAO.getTeacherList();
		model.addObject("teachersList", teachersList);
		//set jsp view
		model.setViewName("teachersAndStudents");
		List<Student> studentsList = studentDAO.getStudentList();
		model.addObject("studentsList", studentsList);
		return model;
	}
	
	@RequestMapping(value = "/teachers")
	public ModelAndView listTeachers(ModelAndView model) {
		List<Teacher> teachersList = teacherDAO.getTeacherList();
		model.addObject("teachersList", teachersList);
		model.setViewName("teachers");
		return model;
	}
	
	@RequestMapping(value = "/teachers/top3")
	public ModelAndView top3Teachers(ModelAndView model) {
		List<Teacher> teachersList = teacherDAO.getTop3();
		model.addObject("teachersList", teachersList);
		model.setViewName("top3teachers");
		return model;
	}
	
	@RequestMapping(value = "/teachers/new", method = RequestMethod.GET)
	public ModelAndView newStudent(ModelAndView model) {
		Teacher newTeacher = new Teacher();
		model.addObject("teacher", newTeacher);
		model.setViewName("addTeacher");
		return model;
	}
	

	@RequestMapping(value = "/teachers/save", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Teacher teacher, @RequestParam Map<String, String> allParams) {
		System.out.println("Parameters are " + allParams.entrySet());
			teacherDAO.createTeacher(teacher);
		return new ModelAndView("redirect:/teachers");
	}
}
