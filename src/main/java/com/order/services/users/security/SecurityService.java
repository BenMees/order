package com.order.services.users.security;

import com.order.domain.users.Feature;
import com.order.domain.users.User;
import com.order.exceptions.UnauthorizedException;
import com.order.services.users.UserService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public void validate(String authorization, Feature feature) {
        String email = decodeAuthorization(authorization);

        User user = userService.getUserByEmail(email);
        if (!user.isAble(feature)){
            throw new UnauthorizedException(user.getEmailAddress(),feature.name());
        }
    }

    public String decodeAuthorization(String authorization) {
        String decodeUserEmail = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        return decodeUserEmail.substring(0, decodeUserEmail.indexOf(":"));
    }

}
