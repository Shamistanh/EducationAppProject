package com.sdp.EducationAppProject.services;

import com.sdp.EducationAppProject.models.XUser;
import com.sdp.EducationAppProject.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoginService {
    private final UserRepo userRepo;

    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String otpGenerator(){
        StringBuilder sb = new StringBuilder();
        String alpha = "1B245DD840UM12QW00@yE";
        Random random = new Random();
        int i=0;
        for(i=0;i<alpha.length();i++){
            sb.append(alpha.charAt(random.nextInt(alpha.length())));

        }
        return sb.toString();
    }

    public void saveToDB(XUser XUser){
        userRepo.save(XUser);
    }

    public XUser getUserByEmail(String email){
        Optional<XUser> userByEmail = userRepo.findByEmail(email);
        return userByEmail.orElse(null);
    }

    public Boolean validate(String email, String password){
       if(userRepo.findByEmail(email).isPresent()) {
           if (userRepo.findByEmail(email).get().getPassword().equals(password)) {
               return true;
           }
           return false;
       }
       return false;
    }

}
