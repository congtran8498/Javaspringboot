package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.JobDB;
import vn.techmaster.demo.modal.Job;

import java.util.List;
@Repository
public class JobDAOImpl implements JobDAO {

    @Override
    public List<Job> findALl(){
        return JobDB.jobList;
    }
    @Override
    public void save(Job job){
        JobDB.jobList.add(job);
    }
    @Override
    public void delete(String id){
        JobDB.jobList.removeIf(job -> job.getId().equals(id));
    }
    @Override
    public List<Job> findByTitleContainsIgnoreCase(String title) {
        return JobDB.jobList.stream()
                .filter(job -> job.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}
