package com.order.services.users;

import com.order.domain.users.Admin;
import com.order.repository.users.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdminByEmail(String email) {
        return adminRepository.getAdmins().stream()
                .filter(admin -> admin.getEmailAddress().equals(email))
                .collect(Collectors.toList());
    }
}
