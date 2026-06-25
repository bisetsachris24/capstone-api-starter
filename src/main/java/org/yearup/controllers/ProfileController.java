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
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    public ProfileController (ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService    = userService;
    }

    // GET http://localhost:8080/profile
    @GetMapping("")
    public ResponseEntity<?> getProfile(Principal principal) {
        // get the currently logged in username
        String userName = principal.getName();
// find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        Profile profiles = profileService.getByUserId(userId);

        return ResponseEntity.ok(profiles);
    }

    // PUT http://localhost:8080/profile
    @PutMapping("")
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile, Principal principal) {
        User user = userService.getByUserName(principal.getName());
        Profile updated = profileService.update(user.getId(), profile);
        return ResponseEntity.ok(updated);
    }
}