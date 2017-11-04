package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.CourseModel;
import com.example.service.CourseService;

@RestController
@RequestMapping("/rest")
public class CourseRestController {

	@Autowired
	CourseService courseService;

	@RequestMapping("/course/view/{idCourse}")
	public CourseModel viewRest(@PathVariable(value = "idCourse") String idCourse) {
		CourseModel course = courseService.selectCourse(idCourse);
		return course;
	}

	@RequestMapping("/course/viewall")
	public List<CourseModel> viewallRest(Model model) {
		List<CourseModel> courses = courseService.selectAllCourse();
		return courses;
	}
	
}
