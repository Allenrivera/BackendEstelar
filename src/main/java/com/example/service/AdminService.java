package com.example.service;


import com.example.model.sesion.Admin;
import com.example.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findByCorreo(String correo) {
        return adminRepository.findByCorreo(correo);
    }
}
