package com.student.controller;

 
import com.student.StudentProperties;
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
	
	 @Inject
	 private StudentProperties studentProperties;
	 @Inject
	 private StudentService studentService;
 
	
    @GetMapping(path = "msg")
	public String getMessage() {
		return studentProperties.getGreeting();
	}
	@GetMapping
	private Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
 

}
