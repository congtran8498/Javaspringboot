package vn.techmaster.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.techmaster.demo.database.TopicDB;
import vn.techmaster.demo.dto.CourseDto;
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
}
