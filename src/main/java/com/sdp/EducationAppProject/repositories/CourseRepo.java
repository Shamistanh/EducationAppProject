package com.sdp.EducationAppProject.repositories;

import com.sdp.EducationAppProject.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepo  extends JpaRepository<Course, Integer> {
}
