package vn.techmaster.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.dto.CourseDto;
import vn.techmaster.demo.service.CourseService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String topic) {
        List<CourseDto> courseDtoList = courseService.getAllCourse(type, name, topic);
        return ResponseEntity.ok(courseDtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("search")
    public ResponseEntity<?> searchPost(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourse(keyword));
    }


}
