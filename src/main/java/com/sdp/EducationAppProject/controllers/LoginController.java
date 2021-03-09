//package com.sdp.EducationAppProject.controllers;
//
//
//import com.sdp.EducationAppProject.enums.ERole;
//import com.sdp.EducationAppProject.mail.JavaMailSender;
//import com.sdp.EducationAppProject.models.MUser;
//import com.sdp.EducationAppProject.models.Role;
//import com.sdp.EducationAppProject.payload.response.MessageResponse;
//import com.sdp.EducationAppProject.repositories.RoleRepository;
//import com.sdp.EducationAppProject.services.LoginService;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//@Log4j2
//@RestController
//@RequestMapping("/")
//public class LoginController {
//
//    public String OTP = "";
//
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JavaMailSender javaMailSender;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    LoginService loginService;
//
//
//
//
//    @PostMapping("signup")
//    public ResponseEntity<?> registration(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam Boolean isTeacher) throws IOException, MessagingException {
//        OTP = loginService.otpGenerator();
//        try{
//           // javaMailSender.sendmail(email, "Mail Confirmation", OTP);
//        }catch (Exception ex){
//            log.error("Not possible to send email");
//        }
//
//        List<Role> roles = Arrays.asList(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
//        roles.stream().forEach(r->roleRepository.save(r));
//        loginService.saveToDB(MUser.builder()
//                .id(UUID.randomUUID().toString())
//                .email(email)
//                .username(username)
//                .isTeacher(isTeacher)
//                .isVerified(false)
//                .roles(roles)
//                .password(encoder.encode(password))
//                .build());
//        log.info("postMapping -> /register");
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
//
////    @PostMapping("verifyOTP")
////    public String verify(@RequestParam String usersOTP, @RequestParam String email) {
////
////        if (usersOTP.equals(OTP)) {
////            loginService.verifyUserByEmail(email);
////            return "otp verification successful";
////        } else {
////            return "otp verification Failed";
////        }
////
////    }
//
//    @GetMapping("getAllUsers")
//    public List<MUser> getAllUsers() {
//        return loginService.getAllUsers();
//    }
//
//
//}
