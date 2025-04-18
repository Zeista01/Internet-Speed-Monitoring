package com.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String branch;

    @Column(name = "enrollment_number")
    private String enrollmentNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Log> logs;

    // Getters and Setters
}
