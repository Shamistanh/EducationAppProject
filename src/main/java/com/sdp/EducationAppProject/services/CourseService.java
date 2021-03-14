package com.sdp.EducationAppProject.services;


import com.sdp.EducationAppProject.models.Course;
import com.sdp.EducationAppProject.models.CourseMaterial;
import com.sdp.EducationAppProject.models.MUser;
import com.sdp.EducationAppProject.repositories.CourseMaterialRepo;
import com.sdp.EducationAppProject.repositories.CourseRepo;
import com.sdp.EducationAppProject.repositories.UserRepo;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final UserRepo userRepo;
    private final CourseMaterialRepo courseMaterialRepo;

    public CourseService(CourseRepo courseRepo, UserRepo userRepo, CourseMaterialRepo courseMaterialRepo) {
        this.courseRepo = courseRepo;
        this.userRepo = userRepo;
        this.courseMaterialRepo = courseMaterialRepo;
    }

    public List<Course> findAllCourses(){
        return courseRepo.findAll();
    }

    public void createCourse(Course course){
        course.setCrsId(UUID.randomUUID().toString());


        course.setCreatedDate(new Date());
        course.setUsrId(currentUser().getId());
        courseRepo.save(course);
    }

    private MUser currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepo.findByUsername(username).get();
    }

    @SneakyThrows
    private byte[] contentDocument(MultipartFile document) {

        try {
            byte[] byteObj = new byte[document.getBytes().length];
            int i = 0;
            for (byte b : document.getBytes()) {
                byteObj[i++] = b;
            }
            return byteObj;


        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    public void addContent(MultipartFile document, String crsId){
       CourseMaterial courseMaterial = new CourseMaterial();
       courseMaterial.setDocument(contentDocument(document));
       courseMaterial.setCourseId(crsId);
       courseMaterial.setIsDocument(true);
       courseMaterial.setDate(new Date());
       courseMaterial.setUserId(currentUser().getId());
       courseMaterialRepo.save(courseMaterial);
        
    }

    public void addContentDocument(String link, String crsId) {
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setLink(link);
        courseMaterial.setCourseId(crsId);
        courseMaterial.setIsDocument(true);
        courseMaterial.setDate(new Date());
        courseMaterial.setUserId(currentUser().getId());
        courseMaterialRepo.save(courseMaterial);
    }
}
