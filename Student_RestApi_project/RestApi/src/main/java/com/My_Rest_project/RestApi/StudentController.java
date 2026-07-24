package com.My_Rest_project.RestApi;

import com.My_Rest_project.RestApi.Service.StudentService;
import com.My_Rest_project.RestApi.model.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET /api/students — list all
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // GET /api/students/{id} — get by roll number
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return service.getStudById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/students — add new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student saved = service.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/students — update existing student
    @PutMapping
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        Student updated = service.updateStudent(student);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/students/{id} — delete by roll number
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    // GET /api/students/search — combined filter with optional params / query search
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Float minMarks,
            @RequestParam(required = false) Float maxMarks) {
        if (query != null && !query.isBlank()) {
            return ResponseEntity.ok(service.searchStudents(query));
        }
        List<Student> results = service.combinedFilter(name, email, minMarks, maxMarks);
        return ResponseEntity.ok(results);
    }
}