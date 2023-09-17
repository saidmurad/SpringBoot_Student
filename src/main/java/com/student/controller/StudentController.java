package com.student.controller;

 
import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Inject
	private StudentProperties studentProperties;
	@Inject
	private StudentService studentService;
 
	
    @GetMapping(path = "/msg")
	public String getMessage(@RequestHeader("user-agent") String userAgent) {
		return studentProperties.getGreeting() + userAgent;
	}
	@GetMapping
	private Collection<Student> getAll() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}

	@GetMapping(path = "/single",
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return studentService.get(optional.orElse(1L));
	}

	@GetMapping("/search/{department}")
	public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department,
														@RequestParam("name") Optional<String> optional) {
		return studentService.getAllStudentsInDepartment(department, optional.orElse(""));
	}

	@PostMapping
	public ResponseEntity<Student> add(@RequestBody Student student) {
		studentService.add(student);
		if(student.getId() > 0) {
			URI url = URI.create("/college/student/" + student.getId());
			return ResponseEntity.accepted().location(url).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
