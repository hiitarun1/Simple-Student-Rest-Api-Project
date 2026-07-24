package com.My_Rest_project.RestApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    @Column(name = "roll", nullable = false, unique = true)
    @Positive(message = "Roll number must be positive")
    private int roll;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "marks")
    @NotNull(message = "Marks are required")
    @Min(value = 0, message = "Marks must be at least 0")
    @Max(value = 100, message = "Marks must be at most 100")
    private Float marks;
}