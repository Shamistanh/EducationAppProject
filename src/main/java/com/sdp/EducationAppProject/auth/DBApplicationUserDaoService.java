package com.sdp.EducationAppProject.auth;


import com.sdp.EducationAppProject.models.MUser;
import com.sdp.EducationAppProject.models.Role;
import com.sdp.EducationAppProject.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("db")
public class DBApplicationUserDaoService implements ApplicationUserDao {

    private final UserRepo myUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBApplicationUserDaoService(UserRepo myUserRepo, PasswordEncoder passwordEncoder) {
        this.myUserRepo = myUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {
        List<Role> roles = myUserRepo.findAll().stream().map(MUser::getRoles).findAny().get();

        HashSet<GrantedAuthority> authorities = new HashSet<>(roles.size());

        List<String> roleNames = roles.stream().map(Role::getName).map(Enum::name).collect(Collectors.toList());
        for (String role : roleNames){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        List<ApplicationUser> applicationUsers =myUserRepo.findAll()
                .stream().map(e-> new ApplicationUser(e.getEmail(), passwordEncoder.encode(e.getPassword()), authorities,true,true,true,true )).collect(Collectors.toList());

        return applicationUsers;
    }

}