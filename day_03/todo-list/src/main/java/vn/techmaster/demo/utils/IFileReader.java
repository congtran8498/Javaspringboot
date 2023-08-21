package vn.techmaster.demo.utils;

import vn.techmaster.demo.modal.Todo;

import java.util.List;

public interface IFileReader {
    List<Todo> readFile(String filePath);
}
