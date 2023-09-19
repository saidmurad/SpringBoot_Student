package com.student.controller;

import com.student.core.College;
import com.student.core.Student;
import com.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/school")
@CrossOrigin
public class SchoolController {

    @Inject
    private StudentService studentService;

    @GetMapping
    public ModelAndView getAllStudents(){
        Collection<College> colleges = studentService.getAllStudents().stream().map(p->p.getCollege()).distinct().collect(Collectors.toList());
        return new ModelAndView("home", "colleges", colleges);
    }
}
