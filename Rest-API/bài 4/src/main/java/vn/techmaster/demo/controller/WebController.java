package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.modal.Job;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class WebController {
    private List<Job> jobList;
    public WebController(){
        jobList = new ArrayList<>();
        jobList.add(new Job(generateUniqueId(),"title 1","des 1","location 1",1000,2000,"email1@gmail.com"));
        jobList.add(new Job(generateUniqueId(),"title 2","des 2","location 2",900,1800,"email1@gmail.com"));
        jobList.add(new Job(generateUniqueId(),"title 3","des 3","location 3",1200,2300,"email1@gmail.com"));
    }
    public String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    //1. lay tat ca job
    @GetMapping("")
    public List<Job> getAllPost() {
        return this.jobList;
    }

    //2. lay job theo id
    @GetMapping("{id}")
    public Job getPostById(@PathVariable String id) {
        return this.jobList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
    }

    //3. tao moi
    @PostMapping("")
    public Job createPost(@RequestBody Job request) {
        Job job = new Job();
        job.setId(generateUniqueId());
        job.setDescription(request.getDescription());
        job.setTitle(request.getTitle());
        job.setLocation(request.getLocation());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setEmailTo(request.getEmailTo());
        this.jobList.add(job);
        return job;
    }

    //4. cap nhat job
    @PutMapping("{id}")
    public Job updatePost(@PathVariable String id, @RequestBody Job request) {
        Job job = this.jobList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
        if(job != null){
            job.setDescription(request.getDescription());
            job.setTitle(request.getTitle());
            job.setLocation(request.getLocation());
            job.setMinSalary(request.getMinSalary());
            job.setMaxSalary(request.getMaxSalary());
            job.setEmailTo(request.getEmailTo());
            return job;
        }
        return null;
    }

    //5. Xoa post
    @DeleteMapping("{id}")
    public void deletePost(@PathVariable String id) {

        this.jobList.removeIf(p -> p.getId().equals(id));
    }

    //6. random job
    @GetMapping("v1/jobs/random")
    public Job randomJob(){
        Random rd = new Random();
        int rdJob = rd.nextInt(jobList.size());
        return jobList.get(rdJob);
    }

    //7. danh sach job
    @GetMapping("v1/sort")
    public List<Job> jobListByDes(@RequestParam String max_salary){
        if(max_salary.equals("desc")){
            Collections.sort(jobList, new Comparator<Job>() {
                @Override
                public int compare(Job o1, Job o2) {
                    return o2.getMaxSalary()-o1.getMaxSalary();
                }
            });
            return this.jobList;
        }
        return null;
    }
}
