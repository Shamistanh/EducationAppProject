package com.sdp.EducationAppProject.repositories;

import com.sdp.EducationAppProject.models.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<XUser, Integer> {

   Optional<XUser> findByEmail(String email);
}
