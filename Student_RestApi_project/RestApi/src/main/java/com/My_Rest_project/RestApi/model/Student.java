package com.My_Rest_project.RestApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Student {


    public int roll;
    public String name;
    public String email;
    float marks;

    public Student(int roll, String name, String email, float marks ) {
        this.roll = roll;
        this.marks = marks;
        this.email = email;
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getMarks() {
        return marks;
    }


    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }
}
