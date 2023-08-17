package vn.techmaster.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.JobDB;
import vn.techmaster.demo.modal.Job;
import vn.techmaster.demo.service.JobService;

import java.util.List;
@Repository
public class JobDao {

    public List<Job> findALl(){
        return JobDB.jobList;
    }
    public void save(Job job){
        JobDB.jobList.add(job);
    }
    public void delete(String id){
        JobDB.jobList.removeIf(job -> job.getId().equals(id));
    }
    public List<Job> findByTitleContainsIgnoreCase(String title) {
        return JobDB.jobList.stream()
                .filter(job -> job.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}
