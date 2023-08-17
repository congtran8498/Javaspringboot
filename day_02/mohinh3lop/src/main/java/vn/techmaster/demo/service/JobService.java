package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.JobDao;
import vn.techmaster.demo.exception.ResouceNotFoundException;
import vn.techmaster.demo.modal.Job;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {
    @Autowired
    private JobDao jobDao;
    public List<Job> getAllJob(){
        return jobDao.findALl();
    }

    public Job getJobById(String id){
        List<Job> jobList = jobDao.findALl();
        return jobList.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->{
                            throw new ResouceNotFoundException("not found");
                });
    }
    public Job createJob(Job request){
        Job job = new Job();
        job.setId(generateUniqueId());
        job.setDescription(request.getDescription());
        job.setTitle(request.getTitle());
        job.setLocation(request.getLocation());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setEmailTo(request.getEmailTo());
        jobDao.save(job);
        return job;
    }
    public String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public Job update(String id, Job request){
        Job job = getJobById(id);
        job.setDescription(request.getDescription());
        job.setTitle(request.getTitle());
        job.setLocation(request.getLocation());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setEmailTo(request.getEmailTo());
        return job;
    }
    public void delete(String id){
        Job job = getJobById(id);
        jobDao.delete(id);
    }
}
