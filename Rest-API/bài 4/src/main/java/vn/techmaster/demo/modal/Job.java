package vn.techmaster.demo.modal;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private String id;
    private String title;
    private String description;
    private String location;
    private int minSalary;
    private int maxSalary;
    private String emailTo;

}
