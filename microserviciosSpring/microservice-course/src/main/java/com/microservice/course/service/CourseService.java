package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.CourseRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public StudentByCourseResponse findStudentsByCourseId(Long courseId){
        // Consultar el curso
        Course course = courseRepository.findById(courseId).orElse(new Course());
        // Consultar estudiantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentsByCourse(courseId);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacherName())
                .studentList(studentDTOList)
                .build();
    }
}
