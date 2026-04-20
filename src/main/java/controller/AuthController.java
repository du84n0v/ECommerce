package controller;

import dto.AuthDto;
import entity.Profile;
import org.springframework.stereotype.Controller;
import service.AuthService;

@Controller
public class AuthController {
    private AuthService service;

    public boolean register(AuthDto dto) {
        return service.save(dto);
    }

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        return service.getProfileByPhoneAndPassword(phone, password);
    }
}
