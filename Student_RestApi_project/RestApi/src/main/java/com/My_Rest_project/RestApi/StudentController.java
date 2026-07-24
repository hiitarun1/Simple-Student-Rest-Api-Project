package com.My_Rest_project.RestApi;

import com.My_Rest_project.RestApi.Service.StudentService;
import com.My_Rest_project.RestApi.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return service.getStudById(id);
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        service.addStudent(student);
        return "Student added successfully";
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student) {
        service.updateStudent(student);
        return "Student updated successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "Student deleted successfully";
    }
}