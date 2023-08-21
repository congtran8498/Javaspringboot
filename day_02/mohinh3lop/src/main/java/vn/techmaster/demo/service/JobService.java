package vn.techmaster.demo.service;

import vn.techmaster.demo.modal.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJob();

    Job getJobById(String id);

    Job createJob(Job request);

    String generateUniqueId();

    Job update(String id, Job request);

    void delete(String id);
}
