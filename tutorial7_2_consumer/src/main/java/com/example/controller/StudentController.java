package com.example.controller;

import java.util.List;

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

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService studentService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/student/add")
	public String add() {
		return "form-add";
	}

	@RequestMapping("/student/add/submit")
	public String addSubmit(@RequestParam(value = "npm", required = false) String npm,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gpa", required = false) String gpa) {
		//if(gpa.)
		if(gpa.equalsIgnoreCase("")){
			return "form-add";	
		}else{
		StudentModel student = new StudentModel(npm, name, Double.parseDouble(gpa), null);
		studentService.addStudent(student);		
		}
		return "success-add";
	}

	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm", required = false) String npm) {
		StudentModel student = studentService.selectStudent(npm);

		if (student != null) {
			model.addAttribute("student", student);
			return "view";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}

	@RequestMapping("/student/view/{npm}")
	public String viewPath(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentService.selectStudent(npm);

		if (student != null) {
			model.addAttribute("student", student);
			return "view";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}

	@RequestMapping("/student/viewall")
	public String view(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);

		return "viewall";
	}

	@RequestMapping("/student/delete/{npm}")
	public String delete(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentService.selectStudent(npm);

		if (student != null) {
			studentService.deleteStudent(npm);
			return "delete";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}
	
	@RequestMapping("/student/update/{npm}")
	public String update(Model model, @PathVariable(value = "npm") String npm) {
		StudentModel student = studentService.selectStudent(npm);		
		if (student != null) {			
			model.addAttribute("student", student);
			return "form-update";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}
	}
	
	@RequestMapping("/student/update/submit")
	public String updateSubmit(Model model, @RequestParam(value = "npm", required = false) String npm,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gpa", required = false) String gpa) 
	{
		StudentModel studentTemp = studentService.selectStudent(npm);		
		if(gpa.equalsIgnoreCase("")){
			model.addAttribute("student", studentTemp);
			return "form-update";	
		}else{
		StudentModel student = new StudentModel(npm, name, Double.parseDouble(gpa),null);	
		studentService.updateStudent(student);
		return "success-update";
		}	
	}
	

    @RequestMapping("/student/updateObject/{npm}")
    public String updateObject(Model model, @PathVariable(value = "npm") String npm) {
        //model.addAttribute("greeting", new Greeting());
    	StudentModel student = studentService.selectStudent(npm);		
		if (student != null) {			
			/* System.out.println(student.getNpm());
			System.out.println(student.getName());
			System.out.println(student.getGpa()); */
			model.addAttribute("student", student);
			return "form-updateObject";
		} else {
			model.addAttribute("npm", npm);
			return "not-found";
		}        		
    }
    
/*    @GetMapping("/student/updateObject/submit")
    public String updateObjectSubmit2(@ModelAttribute StudentModel student) {
    		System.out.print("masuk tidak null");
    		studentService.updateStudent(student);    	
    		return "success-update";    
	}
  */  
    
    @RequestMapping(value = "/student/updateObject/submit", method = RequestMethod.POST)
	public String updateObjectSubmit(Model model, @ModelAttribute StudentModel student) {
    	StudentModel studentTemp = studentService.selectStudent(student.getNpm());		
    	String gpa= Double.toString(studentTemp.getGpa());
    	if(gpa.equalsIgnoreCase("")){
			model.addAttribute("student", studentTemp);
			return "form-update";	
		}else{
    		studentService.updateStudent(student);    	
    		return "success-update";

		}
	}
    
    @RequestMapping("/student/addObject")
	public String addObject() {
		return "form-addObject";
	}
    
    @GetMapping("/student/addObject/submit")
	public String addObjectSubmit(Model model, @ModelAttribute StudentModel student) {
		//if(gpa.)
    	StudentModel studentTemp= new StudentModel("1","1",0,null);
    	model.addAttribute("student", studentTemp);
		studentService.addStudent(student);
		return "success-add";
	}

    
    
}
