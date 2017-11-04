package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.service.CourseService;


@Controller
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping("/course/view")
	public String view(Model model, @RequestParam(value = "id_course", required = false) String idCourse) {
		CourseModel course = courseService.selectCourse(idCourse);
		if (course != null) {
			model.addAttribute("course", course);
			return "viewCourse";
		} else {
			model.addAttribute("idCourse", idCourse);
			return "not-found";
		}
	}

	@RequestMapping("/course/view/{id_course}")
	public String viewPath(Model model, @PathVariable(value = "id_course") String idCourse) {
		CourseModel course = courseService.selectCourse(idCourse);
		if (course != null) {
			model.addAttribute("course", course);
			return "viewCourse";
		} else {
			model.addAttribute("idCourse", idCourse);
			return "not-found";
		}
	}
}
