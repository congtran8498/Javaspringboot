package vn.techmaster.demo.database;

import vn.techmaster.demo.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    public static List<Course> courseList = new ArrayList<>(
            List.of(
                    new Course(1,"Data Structure - Algorithm - LeetCode","description1","online",List.of("backend"),"https://techmaster.vn/resources/image/thumbnail.jpg",1),
                    new Course(2,"Luyện thi FE","description2","offline",List.of("basic","frontend"),"https://media.techmaster.vn/api/static/36/uhWHezPC",2),
                    new Course(3,"Java cấu trúc dữ liệu - giải thuật","description3","offline",List.of("backend"),"https://media.techmaster.vn/api/static/36/bu7v5ak51co41h2qctt0",3),
                    new Course(4,"Java căn bản","description4","online",List.of("basic"),"https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/ZuedW7J1",1),
                    new Course(5,"Spring Boot - Web Back End","description5","online",List.of("backend"),"https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg",2),
                    new Course(6,"Học lập trình Java trực tuyến","description6","offline",List.of("backend","basic"),"https://media.techmaster.vn/api/fileman/Uploads/Java/banner-javabasic.png",3),
                    new Course(7,"Lập trình web với Spring Boot online","description7","offline",List.of("backend"),"https://media.techmaster.vn/api/static/8028/bpfneoc51co8tcg6lek0",1),
                    new Course(8,"Lập trình Web - API - Microservice bằng Golang","description8","online",List.of("database"),"https://media.techmaster.vn/api/static/36/bu803kc51co41h2qctug",2),
                    new Course(9,"Lộ trình đào tạo DevOps","description9","online",List.of("devops"),"https://devops.techmaster.vn/resources/image/banner_devops.png",3),
                    new Course(10,"Nhập môn lập trình - giải thuật cơ bản","description10","offline",List.of("basic"),"https://media.techmaster.vn/api/fileman/Uploads/users/5463/giaithuat-thumnail.png",3),
                    new Course(11,"Lập trình di động IOS Swift online","description11","online",List.of("mobile"),"https://media.techmaster.vn/api/static/8028/bqa348s51cocm3n1ubq0",2),
                    new Course(12,"Lập trình Nodejs (cập nhật 2022)","description12","offline",List.of("backend"),"https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/6PyUoB-T",1)
            )
    );
}
