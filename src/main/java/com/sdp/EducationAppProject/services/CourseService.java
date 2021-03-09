package com.sdp.EducationAppProject.services;


import com.sdp.EducationAppProject.models.Course;
import com.sdp.EducationAppProject.repositories.CourseRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> findAllCourses(){
        return courseRepo.findAll();
    }

    public void createCourse(Course course){
        courseRepo.save(course);
    }
}
