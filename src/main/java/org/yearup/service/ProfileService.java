package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;



@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile getByUserId(int id) { return profileRepository.findById(id).orElseThrow();
    }

    public Profile update(int userId, Profile updated) {
        Profile existing = profileRepository.findById(userId).orElse(null);
        if (existing==null){
            throw new RuntimeException("Profile not found for user ID: " + userId);
        }
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setAddress(updated.getAddress());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setZip(updated.getZip());
        return profileRepository.save(existing);
    }
}
