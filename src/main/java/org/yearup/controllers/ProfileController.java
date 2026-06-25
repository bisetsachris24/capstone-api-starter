package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService    = userService;
    }

    // GET http://localhost:8080/profile
    @GetMapping("")
    public Profile getProfile(Principal principal) {
        User user = userService.getByUserName(principal.getName());
        return profileService.getByUserId(user.getId()).orElseThrow();
    }

    // PUT http://localhost:8080/profile
    @PutMapping("")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile, Principal principal) {
        User user = userService.getByUserName(principal.getName());
        Profile updated = profileService.update(user.getId(), profile);
        return ResponseEntity.ok(updated);
    }
}