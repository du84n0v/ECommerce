package service;

import dto.AuthDto;
import entity.Profile;
import org.springframework.stereotype.Service;
import repository.ProfileRepository;

@Service
public class AuthService {

    private ProfileRepository profileRepository;

    public boolean save(AuthDto dto) {
        Profile profile = profileRepository.getProfileByPhone(dto.phone());
        if(profile != null) return false;
        profile = new Profile();
        profile.setName(dto.name());
        profile.setPhone(dto.phone());
        profile.setPassword(dto.password());
        return profileRepository.save(profile) == 1;
    }

    public Profile getProfileByPhoneAndPassword(String phone, String password) {
        return profileRepository.getProfileByPhoneAndPassword(phone, password);
    }
}
