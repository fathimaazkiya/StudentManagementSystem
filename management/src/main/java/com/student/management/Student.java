package com.student.management;

import jakarta.persistence.*;

@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String email;
    private Double marks;

    // This is a "Constructor" - it's how Java creates a new student
    public Student() {}

    // These are "Getters and Setters" - they let the app read and change the data
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }
}