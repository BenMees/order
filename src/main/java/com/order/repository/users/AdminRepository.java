package com.order.repository.users;

import com.order.domain.users.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AdminRepository {
    private final Map<String, Admin> admins = new ConcurrentHashMap<>();

    public List<Admin> getAdmins() {
        return admins.values().stream().toList();
    }

    public void addAdmin(Admin admin) {
        admins.put(admin.getUniqueId(),admin);
    }
}
