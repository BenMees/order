package com.order.services.users;

import com.order.domain.users.Admin;
import com.order.domain.users.Customer;
import com.order.domain.users.User;
import com.order.exceptions.ObjectDoesNotExist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AdminService adminService;
    private final CustomerService customerService;

    public UserService(AdminService adminService, CustomerService customerService) {
        this.adminService = adminService;
        this.customerService = customerService;
    }

    public User getUserByEmail(String email) {
        List<Admin> adminList = adminService.getAdminByEmail(email);
        if (!adminList.isEmpty()) {
            return adminList.get(0);
        }

        List<Customer> customerList = customerService.getCustomersByEmail(email);
        if (!customerList.isEmpty()) {
            return customerList.get(0);
        }
        throw new ObjectDoesNotExist("User with email address " + email);
    }
}
