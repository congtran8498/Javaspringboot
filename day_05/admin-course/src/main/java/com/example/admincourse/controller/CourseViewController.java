package com.example.admincourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseViewController {
    @GetMapping("/admin/course")
    public String getAdminCourse(){
        return "course";
    }
}
