package testing.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Student2DTO {
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
}