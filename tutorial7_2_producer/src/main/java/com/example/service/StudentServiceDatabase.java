package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService {
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public StudentModel selectStudent(String npm) {
		log.info("select student with npm {}", npm);
		return studentMapper.selectStudent2(npm);
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		log.info("select all students");
		return studentMapper.selectAllStudents2();
	}

	@Override
	public void addStudent(StudentModel student) {
		studentMapper.addStudent(student);
	}

	@Override
	public void deleteStudent(String npm) {
		log.info("hapus students");
		studentMapper.deleteStudent(npm);
	}

	@Override
	public void updateStudent(StudentModel student) {
		// TODO Auto-generated method stub
		log.info ("update students npm {}",student.getNpm());
	 	if (checkNullGpa(student)==false){
	 		log.info ("masuk service gpa tak null");
	 		studentMapper.updateStudent(student);	
	 	}else{
	 		log.info ("masuk service gpa null");
	 	
	 	}
	}
	
	 	@Override
	 	public boolean checkNullGpa(StudentModel student){
		 log.info("check null "+student.getNpm());
		 if ((Double.toString(student.getGpa()).equals(""))){
			 log.info("check null true");
			 return true; 
		 }else{
			 log.info("check null false");
			 return false;
	 }    	
	}

}
