package vn.techmaster.demo.dao;

import vn.techmaster.demo.modal.Job;

import java.util.List;

public interface JobDAO {
    List<Job> findALl();

    void save(Job job);

    void delete(String id);

    List<Job> findByTitleContainsIgnoreCase(String title);
}
