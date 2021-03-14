package com.sdp.EducationAppProject.controllers;


import com.sdp.EducationAppProject.enums.ERole;
import com.sdp.EducationAppProject.models.MUser;
import com.sdp.EducationAppProject.models.Role;
import com.sdp.EducationAppProject.payload.request.LoginRequest;
import com.sdp.EducationAppProject.payload.request.SignupRequest;
import com.sdp.EducationAppProject.payload.response.JwtResponse;
import com.sdp.EducationAppProject.payload.response.MessageResponse;
import com.sdp.EducationAppProject.repositories.RoleRepository;
import com.sdp.EducationAppProject.repositories.UserRepository;
import com.sdp.EducationAppProject.security.JwtUtils;
import com.sdp.EducationAppProject.security.UserDetailsImpl;
import com.sdp.EducationAppProject.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        loginService.saveSessionInfoToDb( userDetails.getId(),loginRequest.getUsername(),jwt, roles);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                roles));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Valid SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        MUser user = new MUser(UUID.randomUUID().toString(),signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();


        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(new Role(UUID.randomUUID().toString(),ERole.ROLE_USER));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":

//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                               // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        .orElse(new Role());
                        roles.add(new Role(UUID.randomUUID().toString(),ERole.ROLE_ADMIN));

                        break;
                    case "instructor":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(new Role(UUID.randomUUID().toString(),ERole.ROLE_INSTRUCTOR));

                        break;
                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(new Role(UUID.randomUUID().toString(),ERole.ROLE_USER));
                }
            });
        }

        user.setRoles(roles);
        roles.stream().forEach(r->roleRepository.save(r));
        userRepository.save(user);
        System.out.println(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}