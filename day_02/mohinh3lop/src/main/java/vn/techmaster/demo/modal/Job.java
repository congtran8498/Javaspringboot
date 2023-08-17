package vn.techmaster.demo.modal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private String id;
    @NotNull(message = "Tiêu đề không được để trống")
    @Size(min = 2, max = 50, message = "Tiêu đề phải từ 2 đến 50 ký tự")
    private String title;
    private String description;
    private String location;
    private int minSalary;
    private int maxSalary;
    private String emailTo;

}
