package com.My_Rest_project.RestApi.Repository;

import com.My_Rest_project.RestApi.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepo {

    List<Student> list=new ArrayList<>(List.of(
            new Student(1,"tarun","abc@123",23.20f),
            new Student(2,"mukesh","dgs@123.com",93.89f),
            new Student(3,"rahul","eyb@123.com",83.89f),
            new Student(4,"shruti","kdd@123.com",13.89f)
    ));


    public List<Student> getAllStudents() {
        return list;
    }

    public Student getStudById(int id) {
        for(Student ele : list){
            if(ele.roll==id){
                return ele;
            }
        }
        return null;
    }

    public void addStudent(Student addstud) {
        list.add(addstud);

    }

    public void updateStudent(Student stud) {
        for(Student obj : list){
            if(obj.roll==stud.roll){
                obj.setRoll(stud.getRoll());
                obj.setName(stud.getName());
                obj.setEmail(stud.getEmail());
                obj.setMarks(stud.getMarks());
            }
        }
    }

    public void deleteStudent(int id) {
        for(Student obj : list){
            if(obj.getRoll()==id){
                list.remove(obj);
            }
        }
    }
}
