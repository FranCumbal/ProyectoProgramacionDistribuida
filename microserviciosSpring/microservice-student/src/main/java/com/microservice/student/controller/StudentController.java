package com.microservice.student.controller;

import com.microservice.student.entities.Student;
import com.microservice.student.service.IStudentService;
import com.microservice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> findAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.save(student);
    }

    @GetMapping("/search_by_courseId/{courseId}")
    public ResponseEntity<?> findByIdCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(studentService.findByIdCourse(courseId));
    }
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarStudent(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
