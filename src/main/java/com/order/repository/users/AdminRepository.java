package com.order.repository.users;

import com.order.domain.users.Address;
import com.order.domain.users.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AdminRepository {
    private final Map<String, Admin> admins = new ConcurrentHashMap<>();

    public AdminRepository() {
        Address address = new Address("SesameStreet", "210", "3000", "Leuven");
        Admin admin = new Admin("Default", "Admin", "admin@order.com", address, "04");
        addAdmin(admin);
    }

    public List<Admin> getAdmins() {
        return admins.values().stream().toList();
    }

    public void addAdmin(Admin admin) {
        admins.put(admin.getUniqueId(),admin);
    }
}
