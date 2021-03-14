package com.sdp.EducationAppProject.controllers;


import com.sdp.EducationAppProject.models.Course;
import com.sdp.EducationAppProject.models.CourseMaterial;
import com.sdp.EducationAppProject.services.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("findAllCourses")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Course> findAllCourses() {
        log.info("getMapping -> /findAllCourses");
        return courseService.findAllCourses();
    }
    @PostMapping("createCourse")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void createCourse(Course course){
        log.info("postMapping -> /createCourse");
        courseService.createCourse(course);
    }

    @PostMapping("addContentDocument")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void addContentDocument(@RequestParam("link") String link,
                                   @RequestParam("crsId") String crsId){
        log.info("postMapping -> /addContent");
        courseService.addContentDocument(link,crsId);
    }
    @PostMapping("addContent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void addContentDocument(@RequestParam("document") MultipartFile document,
                                   @RequestParam("crsId") String crsId){
        log.info("postMapping -> /addContent");
        courseService.addContent(document,crsId);
    }

}
