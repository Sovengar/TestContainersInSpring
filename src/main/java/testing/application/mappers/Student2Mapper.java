package testing.application.mappers;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import testing.application.dtos.Student2DTO;
import testing.domain.models.Student2;

@Component
public class Student2Mapper {

    public Student2DTO toDTO(@NonNull Student2 student2) {
        return Student2DTO.builder()
                .firstname(student2.getFirstname())
                .lastname(student2.getLastname())
                .email(student2.getEmail())
                .age(student2.getAge())
                .build();
    }

    public Student2 toEntity(@NonNull Student2DTO student2DTO) {
        Student2 student2 = new Student2();

        student2.setFirstname(student2DTO.getFirstname());
        student2.setLastname(student2DTO.getLastname());
        student2.setEmail(student2DTO.getEmail());
        student2.setAge(student2DTO.getAge());

        return student2;
    }
}