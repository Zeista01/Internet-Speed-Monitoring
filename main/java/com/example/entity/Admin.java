package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String profession;

    @Column(name = "college_id")
    private String collegeId;

    // Getters and Setters
}
