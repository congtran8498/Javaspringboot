package vn.techmaster.demo.dao;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.database.CourseDB;
import vn.techmaster.demo.model.Course;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAOImpl implements CourseDAO{
    @Override
    public List<Course> findAll() {
        return CourseDB.courseList;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return CourseDB.courseList.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Course> findByTitleContainsIgnoreCase(String title) {
        return CourseDB.courseList.stream()
                .filter(post -> post.getName().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    @Override
    public void save(Course course) {
        CourseDB.courseList.add(course);
    }

    @Override
    public void delete(Course course) {
        CourseDB.courseList.remove(course);
    }
}
