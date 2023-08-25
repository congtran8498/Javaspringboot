package vn.techmaster.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.database.TopicDB;

@CrossOrigin
@RestController
@RequestMapping("api/v1/topics")
public class TopicController {
    @GetMapping
    public ResponseEntity<?> getAllTopic() {
        return ResponseEntity.ok(TopicDB.topicList);
    }
}