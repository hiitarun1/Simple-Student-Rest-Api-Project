package com.My_Rest_project.RestApi;

import com.My_Rest_project.RestApi.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ViewController {

        private final StudentService service;

        public ViewController(StudentService service) {
            this.service = service;
        }

        @GetMapping("/")
        public String index(Model model) {

            model.addAttribute("students", service.getAllStudents());

            return "index";
        }

        @GetMapping("/add")
        public String addStudentPage() {
            return "add-student";
        }

        @GetMapping("/edit")
        public String editStudentPage() {
            return "edit-student";
        }
}
