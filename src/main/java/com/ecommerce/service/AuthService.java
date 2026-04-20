package com.ecommerce.service;

import com.ecommerce.dto.AuthDto;
import com.ecommerce.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.repository.ProfileRepository;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public boolean save(AuthDto dto) {
        Profile profile = profileRepository.getProfileByPhone(dto.phone());
        if(profile != null) return false;
        profile = new Profile();
        profile.setName(dto.name());
        profile.setPhone(dto.phone());
        profile.setPassword(dto.password());
        profile.setBalance(0D);
        return profileRepository.save(profile) == 1;
    }

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        return profileRepository.getProfileByPhoneAndPassword(phone, password);
    }
}
