package testing.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testing.domain.models.Student2;
import testing.domain.repositories.Student2Repository;
import testing.application.dtos.Student2DTO;
import testing.application.mappers.Student2Mapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Student2Service {
    private final Student2Repository student2Repository;
    private final Student2Mapper student2Mapper;

    public List<Student2> getAllStudents() {
        return student2Repository.findAll();
    }

    public Student2 getStudentById(Long id) {
        return student2Repository.findById(id).orElse(null);
    }

    public Student2 create(Student2DTO dto) {
        var student2 = student2Mapper.toEntity(dto);
        return student2Repository.save(student2);
    }

    public Student2 update(Long id, Student2DTO dto) {
        var student2 = student2Mapper.toEntity(dto);
        student2.setId(id);

        student2 = student2Repository.save(student2);
        return student2Repository.save(student2);
    }

    public void deleteStudent(Long id) {
        student2Repository.deleteById(id);
    }
}
