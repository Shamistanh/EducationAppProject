package com.sdp.EducationAppProject.controllers;


import com.sdp.EducationAppProject.mail.JavaMailSender;
import com.sdp.EducationAppProject.models.XUser;
import com.sdp.EducationAppProject.services.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/")
public class LoginController {

    public String OTP="";

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    LoginService loginService;

    @PostMapping("login")
    public Boolean login(@RequestParam String email, @RequestParam String password) throws IOException, MessagingException {
        log.info("postMapping -> /login");
        return loginService.validate(email, password);
    }

    @PostMapping("register")
    public String registration(@RequestParam String fullName, @RequestParam String email, @RequestParam String password, @RequestParam Boolean isTeacher) throws IOException, MessagingException {
        OTP = loginService.otpGenerator();
        javaMailSender.sendmail(email,"Mail Confirmation",OTP);
        loginService.saveToDB(XUser.builder().id("aa")
                .fullName(fullName).password(password).email(email).isTeacher(isTeacher).isVerified(false).build());
        log.info("postMapping -> /register");
        return "register";
    }

    @PostMapping("verifyOTP")
    public String verify(@RequestParam String usersOTP, @RequestParam String email) {

        if (usersOTP.equals(OTP)){
            loginService.getUserByEmail(email).setIsVerified(true);

            return "otp verification successful";
        }else {
            return "otp verification Failed";
        }

    }






}
