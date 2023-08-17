package vn.techmaster.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.modal.Job;
import vn.techmaster.demo.response.ErrorResponse;
import vn.techmaster.demo.service.JobService;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JobController {
    @Autowired
    private JobService jobService;

    //1. lay tat ca job
    @GetMapping("")
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.ok(jobService.getAllJob());
    }

    //2. lay job theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getJobById(id));//status 200
    }

    //3. tao moi
    @PostMapping("")
    public ResponseEntity<?> createJob(@Valid @RequestBody Job request) {
        return new ResponseEntity<>(jobService.createJob(request), HttpStatus.CREATED); //status 201
    }

    //4. cap nhat job
    @PutMapping("{id}")
    public ResponseEntity<?> updatePost(@PathVariable String id, @Valid @RequestBody Job request) {
        return ResponseEntity.ok(jobService.update(id, request));
    }

    //5. Xoa post
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build(); //status 204
    }

    //6. random job
//    @GetMapping("v1/jobs/random")
//    public Job randomJob(){
//        Random rd = new Random();
//        int rdJob = rd.nextInt(jobList.size());
//        return jobList.get(rdJob);
//    }

    //7. danh sach job
//    @GetMapping("v1/sort")
//    public List<Job> jobListByDes(@RequestParam String max_salary){
//        if(max_salary.equals("desc")){
//            Collections.sort(jobList, new Comparator<Job>() {
//                @Override
//                public int compare(Job o1, Job o2) {
//                    return o2.getMaxSalary()-o1.getMaxSalary();
//                }
//            });
//            return this.jobList;
//        }
//        return null;
//    }
}
