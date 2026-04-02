package com.My_Rest_project.RestApi;

import com.My_Rest_project.RestApi.Service.StudentService;
import com.My_Rest_project.RestApi.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class StudentController {

    private final StudentService service;
    public StudentController(StudentService service){
        this.service=service;
    }
    @GetMapping("/student")
    public List<Student> showAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id){
        return service.getStudById(id);

    }

    @PostMapping("/student")
    public String AddStudent(@RequestBody Student addstud){
        service.addStudent(addstud);
        return "student added successfully";
    }

    @PutMapping("/student")
    public String updateStudent(@RequestBody Student stud){
        service.updateStudent(stud);
        return "updted";
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable int id){
        service.deleteStudent(id);
        return "student deleted successfully";
    }
}
