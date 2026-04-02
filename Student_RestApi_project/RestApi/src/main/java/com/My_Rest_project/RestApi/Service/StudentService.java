package com.My_Rest_project.RestApi.Service;

import com.My_Rest_project.RestApi.Repository.StudentRepo;
import com.My_Rest_project.RestApi.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

   private final StudentRepo repo;

   //constructor injection
   StudentService (StudentRepo repo){
       this.repo=repo;
    }

    public List<Student> getAllStudents(){
       return repo.getAllStudents();
    }


    public Student getStudById(int id) {
       return repo.getStudById(id);
    }

    public void addStudent(Student addstud) {
       repo.addStudent(addstud);
    }

    public void updateStudent(Student stud) {
       repo.updateStudent(stud);
    }

    public void deleteStudent(int id) {
       repo.deleteStudent(id);
    }
}
