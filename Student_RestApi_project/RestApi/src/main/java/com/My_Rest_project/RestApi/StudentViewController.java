package com.My_Rest_project.RestApi;

import com.My_Rest_project.RestApi.Service.StudentService;
import com.My_Rest_project.RestApi.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students-view")
@RequiredArgsConstructor
public class StudentViewController {

    private final StudentService service;

    // Show list of students (index page)
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "index";
    }

    // Show add student form
    @GetMapping("/add")
    public String addStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Handle add student form submission
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        service.addStudent(student);
        return "redirect:/students-view";
    }

    // Show edit student form for a specific roll
    @GetMapping("/edit/{roll}")
    public String editStudentPage(@PathVariable int roll, Model model) {
        Student student = service.getStudById(roll);
        model.addAttribute("student", student);
        return "edit-student";
    }

    // Handle edit student form submission
    @PostMapping("/edit/{roll}")
    public String editStudent(@PathVariable int roll, @ModelAttribute Student student) {
        // The roll field is read‑only in the form; ensure it matches the path variable
        service.updateStudent(student);
        return "redirect:/students-view";
    }

    // Delete a student and redirect back to index
    @GetMapping("/delete/{roll}")
    public String deleteStudent(@PathVariable int roll) {
        service.deleteStudent(roll);
        return "redirect:/students-view";
    }
}
