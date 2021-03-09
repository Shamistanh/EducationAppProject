package com.sdp.EducationAppProject.services;

import com.sdp.EducationAppProject.models.MUser;
import com.sdp.EducationAppProject.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
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
        String alpha = "1298345623789";
        Random random = new Random();
        int i;
        for(i=0;i<alpha.length();i++){
            sb.append(alpha.charAt(random.nextInt(alpha.length())));

        }
        return sb.toString();
    }

    public void saveToDB(MUser XUser){
        userRepo.save(XUser);
    }

    public MUser getUserByEmail(String email){
        Optional<MUser> userByEmail = userRepo.findByEmail(email);
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

    public List<MUser> getAllUsers() {
        return userRepo.findAll();
    }

//    public void verifyUserByEmail(String email) {
//        MUser userByEmail = getUserByEmail(email);
//        userByEmail.setIsVerified(true);
//        userRepo.deleteByEmail(email);
//        saveToDB(userByEmail);
//    }
}
