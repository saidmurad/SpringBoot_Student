package com.student.controller;

 
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	 @Value("${greeting}")
	 private String message;
	 @Inject
	 private StudentService studentService;
 
	
    @GetMapping(path = "msg")
	public String getMessage() {
		return message;
	}
	@GetMapping
	private Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
 

}
