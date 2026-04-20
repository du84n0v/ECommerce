package com.ecommerce.controller;

import com.ecommerce.dto.AuthDto;
import com.ecommerce.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ecommerce.service.AuthService;

@Controller
public class AuthController {
    @Autowired
    private AuthService service;

    public boolean register(AuthDto dto) {
        return service.save(dto);
    }

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        return service.getProfileByPhoneAndPassword(phone, password);
    }
}
