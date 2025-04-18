package com.example.controller;

import com.example.entity.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @PostMapping("/register")
    public String registerAdmin(@RequestBody Admin adminDetails) {
        return "Registered Admin: " + adminDetails.getName() +
               ", Profession: " + adminDetails.getProfession() +
               ", College ID: " + adminDetails.getCollegeId();
    }
}
