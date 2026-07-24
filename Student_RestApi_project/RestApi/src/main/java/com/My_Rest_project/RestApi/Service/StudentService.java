package com.My_Rest_project.RestApi.Service;

import com.My_Rest_project.RestApi.Repository.StudentRepo;
import com.My_Rest_project.RestApi.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo repo;

    // Constructor injection (no @Autowired needed for single constructor)
    StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Get student by roll number
    public Optional<Student> getStudById(int id) {
        return repo.findById(id);
    }

    // Add a new student
    public Student addStudent(Student student) {
        return repo.save(student);
    }

    // Update an existing student
    public Student updateStudent(Student student) {
        return repo.save(student);
    }

    // Delete a student by roll number
    public void deleteStudent(int id) {
        repo.deleteById(id);
    }

    // Search by name (case-insensitive, partial match)
    public List<Student> searchByName(String name) {
        return repo.searchByName(name);
    }

    // Search by email (case-insensitive, partial match)
    public List<Student> searchByEmail(String email) {
        return repo.searchByEmail(email);
    }

    // Filter by minimum marks
    public List<Student> filterByMinMarks(Float minMarks) {
        return repo.findByMinMarks(minMarks);
    }

    // Filter by maximum marks
    public List<Student> filterByMaxMarks(Float maxMarks) {
        return repo.findByMaxMarks(maxMarks);
    }

    // Filter between two marks (inclusive)
    public List<Student> filterBetweenMarks(Float min, Float max) {
        return repo.findByMarksBetween(min, max);
    }

    // Combined filter — all parameters optional
    public List<Student> combinedFilter(String name, String email, Float minMarks, Float maxMarks) {
        // Treat empty strings as null so the JPQL ignores them
        String safeName = (name != null && !name.isBlank()) ? name : null;
        String safeEmail = (email != null && !email.isBlank()) ? email : null;
        return repo.combinedFilter(safeName, safeEmail, minMarks, maxMarks);
    }

    // General search by name OR email
    public List<Student> searchStudents(String query) {
        if (query == null || query.isBlank()) {
            return repo.findAll();
        }
        return repo.searchStudents(query.trim());
    }
}
