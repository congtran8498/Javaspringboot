package vn.techmaster.demo.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertCourseRequest {
    @NotNull(message = "name không được để trống")
    @NotEmpty(message = "name không được để trống")
    private String name;

    @NotNull(message = "description không được để trống")
    @NotEmpty(message = "description không được để trống")
    @Size(min = 10)
    private String description;

    @NotNull(message = "type không được để trống")
    @NotEmpty(message = "type không được để trống")
    private String type;
    private List<String> topics;
    private String thumbnail;

    @NotNull(message = "userId không được để trống")
    private Integer userId;
}
