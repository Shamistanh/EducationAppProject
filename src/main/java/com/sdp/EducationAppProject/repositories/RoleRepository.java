package com.sdp.EducationAppProject.repositories;


import com.sdp.EducationAppProject.enums.ERole;
import com.sdp.EducationAppProject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
