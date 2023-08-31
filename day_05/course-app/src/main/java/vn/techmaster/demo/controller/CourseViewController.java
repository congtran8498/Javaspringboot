package vn.techmaster.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.database.TopicDB;
import vn.techmaster.demo.database.UserDB;
import vn.techmaster.demo.dto.CourseDto;
import vn.techmaster.demo.request.UpsertCourseRequest;
import vn.techmaster.demo.service.CourseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CourseViewController {
    private final CourseService courseService;

    public CourseViewController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/khoa-hoc")
    public String getCoursePage(Model model){
        List<CourseDto> courseDtoList = courseService.getAllCourse(null, null, null);
        model.addAttribute("courselist",courseDtoList);
        model.addAttribute("topicList", TopicDB.topicList);
        return "course-list";
    }

    @GetMapping("/khoa-hoc/online")
    public String getOnlinePage(Model model){
        List<CourseDto> courseOnlineList = courseService.getAllCourse("online", null, null);
        Set<String> topic = new HashSet<>();
        for(CourseDto c : courseOnlineList){
            topic.addAll(c.getTopics());
        }
        model.addAttribute("topicOnline", topic);
        model.addAttribute("courseOnline", courseOnlineList);

        return "online";
    }

    @GetMapping("/khoa-hoc/onlab")
    public String getOnlabPage(Model model){
        List<CourseDto> courseOnlabList = courseService.getAllCourse("offline", null, null);
        Set<String> topic = new HashSet<>();
        for(CourseDto c : courseOnlabList){
            for(String s : c.getTopics()){
               topic.add(s);
            }
        }
        model.addAttribute("topicOnlab", topic);
        model.addAttribute("courseOnlab", courseOnlabList);
        return "onlab";
    }

    @GetMapping("/khoa-hoc/{id}")
    public String getDetailPage(Model model, @PathVariable Integer id){
        CourseDto courseDto = courseService.getCourseById(id);
        model.addAttribute("detailCourse", courseDto);
        return "detail";
    }

    @GetMapping("/admin/course")
    public String getAdminCourse(Model model){
        List<CourseDto> courseDtoList = courseService.getAllCourse(null, null, null);
        model.addAttribute("courselist",courseDtoList);
        return "admin-course";
    }
    @GetMapping("/admin/create")
    public String getAdminCreate(Model model){
        model.addAttribute("topicList", TopicDB.topicList);
        model.addAttribute("userList", UserDB.userList);
        return "admin-create";
    }
    @GetMapping("/admin/course/{id}")
    public String getAdminDetail(Model model, @PathVariable Integer id){
        CourseDto courseDto = courseService.getCourseById(id);
        model.addAttribute("detailAdmin", courseDto);
        model.addAttribute("topicList", TopicDB.topicList);

        return "admin-detail";
    }

    //Viet API admin tra ve json

    //1. lay danh sach khoa hoc
    @GetMapping("/api/v1/admin/courses")
    public ResponseEntity<?> getAllCourse(){
        List<CourseDto> courseDtoList = courseService.getAllCourse(null,null,null);
        return ResponseEntity.ok(courseDtoList);
    }

    //2. tao moi khoa hoc
    @PostMapping("/api/v1/admin/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody UpsertCourseRequest request){
        return new ResponseEntity<>(courseService.createCourse(request),  HttpStatus.CREATED);
    }

    //3. lay chi tiet khoa hoc
    @GetMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody UpsertCourseRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(courseService.updateCourse(request,id));
    }

    @DeleteMapping("/api/v1/admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
