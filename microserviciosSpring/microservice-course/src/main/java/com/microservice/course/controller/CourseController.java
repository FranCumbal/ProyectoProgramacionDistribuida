package com.microservice.course.controller;

import com.microservice.course.entities.Course;
import com.microservice.course.service.ICourseService;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private StudentClient studentClient;

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearCourse(@RequestBody Course course) {
        courseService.save(course);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarCourse(@RequestBody Course course, @PathVariable Long id) {
        course.setId(id);
        courseService.save(course);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @GetMapping("/search-students/{courseId}")
    public ResponseEntity<StudentByCourseResponse> findStudentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.findStudentsByCourseId(courseId));
    }
}
