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

import uniManagement.sap.dao.CourseDAO;
import uniManagement.sap.model.Course;

@Controller
public class CourseController {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@RequestMapping(value = "/courses")
	public ModelAndView listCourses(ModelAndView model) {
		List<Course> courseList = courseDAO.getCourseList();
		model.addObject("courseList", courseList);
		model.setViewName("courses");
		return model;
	}
	
	@RequestMapping(value = "/courses/top3")
	public ModelAndView top3Courses(ModelAndView model) {
		List<Course> courseList = courseDAO.getTop3Courses();
		model.addObject("courseList", courseList);
		model.setViewName("top3courses");
		return model;
	}
	
	@RequestMapping(value = "/courses/new", method = RequestMethod.GET)
	public ModelAndView newCourse(ModelAndView model) {
		Course newCourse = new Course();
		model.addObject("course", newCourse);
		model.setViewName("addCourse");
		return model;
	}
	
	@RequestMapping(value = "/courses/save", method = RequestMethod.POST)
	public ModelAndView saveCourse(@ModelAttribute Course course, @RequestParam Map<String, String> allParams) {
		// depending on input parameters determine whether to create a student or
		// student + course;
		String idAsString = allParams.get("currentTeacherID");
		int teacher_id = Integer.valueOf(idAsString);
		System.out.println("Parameters are " + allParams.entrySet());
		courseDAO.createCourse(course, teacher_id);
		return new ModelAndView("redirect:/courses");
	}
}
