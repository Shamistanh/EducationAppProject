package com.sdp.EducationAppProject.services;

import com.sdp.EducationAppProject.models.EduSession;
import com.sdp.EducationAppProject.models.MUser;
import com.sdp.EducationAppProject.repositories.SessionRepo;
import com.sdp.EducationAppProject.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {
    private final UserRepo userRepo;
    private final SessionRepo sessionRepo;

    public LoginService(UserRepo userRepo, SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
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

    public void saveSessionInfoToDb(String id, String username, String jwt, List<String> roles) {

        roleFormatter(roles);
        EduSession session = new EduSession();
        session.setAccessToken(jwt);
        session.setRoles(roleFormatter(roles));
        session.setId(UUID.randomUUID().toString());
        session.setUserId(id);
        session.setCreatedDate(new Date());
        session.setUsername(username);
        sessionRepo.save(session);
    }

    private String roleFormatter(List<String> roles) {
        StringBuilder sb = new StringBuilder();
        for(String role : roles){
            sb.append(":");
            sb.append(role);
        }
        return sb.toString();

    }

//    public void verifyUserByEmail(String email) {
//        MUser userByEmail = getUserByEmail(email);
//        userByEmail.setIsVerified(true);
//        userRepo.deleteByEmail(email);
//        saveToDB(userByEmail);
//    }
}
