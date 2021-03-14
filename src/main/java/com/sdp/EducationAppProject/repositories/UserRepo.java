package com.sdp.EducationAppProject.repositories;

import com.sdp.EducationAppProject.models.MUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<MUser, Integer> {

   Optional<MUser> findByEmail(String email);
   void deleteByEmail(String email);

   Optional<MUser> findByUsername(String username);

   @Override
   Page<MUser> findAll(Pageable pageable);
}
