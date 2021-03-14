package com.sdp.EducationAppProject.repositories;


import com.sdp.EducationAppProject.models.EduSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<EduSession, Integer> {

}
