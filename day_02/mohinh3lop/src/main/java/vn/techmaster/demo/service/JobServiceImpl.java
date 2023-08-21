package vn.techmaster.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.JobDAO;
import vn.techmaster.demo.exception.ResouceNotFoundException;
import vn.techmaster.demo.modal.Job;

import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDAO jobDAO;
    @Override
    public List<Job> getAllJob(){
        return jobDAO.findALl();
    }

    @Override
    public Job getJobById(String id){
        List<Job> jobList = jobDAO.findALl();
        return jobList.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->{
                            throw new ResouceNotFoundException("not found");
                });
    }
    @Override
    public Job createJob(Job request){
        Job job = new Job();
        job.setId(generateUniqueId());
        job.setDescription(request.getDescription());
        job.setTitle(request.getTitle());
        job.setLocation(request.getLocation());
        job.setMinSalary(request.getMinSalary());
        job.setMaxSalary(request.getMaxSalary());
        job.setEmailTo(request.getEmailTo());
        jobDAO.save(job);
        return job;
    }
    @Override
    public String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    @Override
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
    @Override
    public void delete(String id){
        Job job = getJobById(id);
        jobDAO.delete(id);
    }
}
