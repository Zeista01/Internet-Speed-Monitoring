package com.example.controller;

import com.example.entity.Admin;
import com.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable int id) {
        return adminRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            admin.setUsername(adminDetails.getUsername());
            admin.setPassword(adminDetails.getPassword());
            admin.setName(adminDetails.getName());
            admin.setProfession(adminDetails.getProfession());
            admin.setCollegeId(adminDetails.getCollegeId());
            return adminRepository.save(admin);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable int id) {
        adminRepository.deleteById(id);
    }
}
