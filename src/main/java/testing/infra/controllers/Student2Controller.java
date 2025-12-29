package testing.infra.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import testing.domain.models.Student2;
import testing.application.dtos.Student2DTO;
import testing.application.Student2Service;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class Student2Controller {
    private final Student2Service student2Service;

    @GetMapping
    public List<Student2> getAllStudents() {
        return student2Service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student2 getStudentById(@PathVariable Long id) {
        return student2Service.getStudentById(id);
    }

    @PostMapping
    public Student2 createStudent(@RequestBody Student2DTO student) {
        return student2Service.create(student);
    }

    @PutMapping("/{id}")
    public Student2 updateStudent(@PathVariable Long id, @RequestBody Student2DTO student) {
        return student2Service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        student2Service.deleteStudent(id);
    }
}