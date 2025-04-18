package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public String registerUser(@RequestBody User userDetails) {
        return "Registered User: " + userDetails.getName() +
               ", Branch: " + userDetails.getBranch() +
               ", Enrollment No.: " + userDetails.getEnrollmentNumber();
    }
}
