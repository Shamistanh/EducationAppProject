package com.sdp.EducationAppProject.controllers;


import com.sdp.EducationAppProject.models.Course;
import com.sdp.EducationAppProject.services.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("findAllCourses")
    public List<Course> findAllCourses() {
        log.info("getMapping -> /findAllCourses");
        return courseService.findAllCourses();
    }
    @PostMapping("createCourse")
    public void createCourse(Course course){
        log.info("postMapping -> /createCourse");
        courseService.createCourse(course);
    }
}
