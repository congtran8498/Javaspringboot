package vn.techmaster.demo.service;

import vn.techmaster.demo.dto.CourseDto;
import vn.techmaster.demo.model.Course;
import vn.techmaster.demo.request.UpsertCourseRequest;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourse(String type, String name, String topic);
    CourseDto getCourseById(Integer id);
    List<Course> searchCourse(String title);
    Course createCourse(UpsertCourseRequest request);
    Course updateCourse(UpsertCourseRequest request, Integer id);
    void deleteCourse(Integer id);
}
