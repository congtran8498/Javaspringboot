package vn.techmaster.demo.util;

import vn.techmaster.demo.modal.Job;

import java.util.List;

public interface iFileReader {
    List<Job> readFile(String filePath);
}
