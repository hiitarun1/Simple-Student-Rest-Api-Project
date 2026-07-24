package com.My_Rest_project.RestApi.Repository;

import com.My_Rest_project.RestApi.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepo {
    private final List<Student> list = new ArrayList<>(List.of(
            new Student(1, "tarun", "abc@123", 23.20f),
            new Student(2, "mukesh", "dgs@123.com", 93.89f),
            new Student(3, "rahul", "eyb@123.com", 83.89f),
            new Student(4, "shruti", "kdd@123.com", 13.89f)
    ));

    public List<Student> getAllStudents() {
        return list;
    }

    public Student getStudById(int id) {
        return list.stream()
                .filter(s -> s.getRoll() == id)
                .findFirst()
                .orElse(null);
    }

    public void addStudent(Student addstud) {
        list.add(addstud);
    }

    public void updateStudent(Student stud) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.getRoll() == stud.getRoll()) {
                list.set(i, stud);
                break;
            }
        }
    }

    public void deleteStudent(int id) {
        // Safe removal without ConcurrentModificationException
        list.removeIf(s -> s.getRoll() == id);
    }
}
