package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.CourseModel;

@Service
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CourseModel selectCourse(String idCourse) {
		// TODO Auto-generated method stub
		CourseModel course = restTemplate.getForObject("http://localhost:8080/rest/course/view/" + idCourse,
				CourseModel.class);
		return course;
	}

	@Override
	public List<CourseModel> selectAllCourses() {
		// TODO Auto-generated method stub
		List<CourseModel> courses = restTemplate.getForObject("http://localhost:8080/rest/course/viewall",
				List.class);
		return courses;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}
	
}
