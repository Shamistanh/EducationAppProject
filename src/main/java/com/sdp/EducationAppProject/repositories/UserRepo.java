package com.sdp.EducationAppProject.repositories;

import com.sdp.EducationAppProject.models.XUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<XUser, Integer> {

   Optional<XUser> findByEmail(String email);
   void deleteByEmail(String email);

   @Override
   Page<XUser> findAll(Pageable pageable);
}
