package com.sajal.student_management.service.impl;

import com.sajal.student_management.entity.Courses;
import com.sajal.student_management.repository.CourseRepository;
import com.sajal.student_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Courses addCourse(Courses courses) throws Exception {
        if(courseExist(courses.getCourseId())){
            throw new Exception("Course with matching id already exist");
        }
       return courseRepository.save(courses);
    }

    @Override
    public Courses getCourseById(String courseId) throws Exception {
        if(!courseExist(courseId)){
            throw new Exception("no course found with the Id");
        }
        return courseRepository.findById(courseId).get();
    }

    @Override
    public void deleteCourseById(String courseId) throws Exception {
        if(!courseExist(courseId)){
            throw new Exception("no course found with the Id");
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public Set<Courses> getAllCourses() {
        return courseRepository.findAll().stream().collect(Collectors.toSet());
    }

    private boolean courseExist(String courseId) {
        if(courseRepository.existsById(courseId)){
            return true;
        }
        else{
            return false;
        }
    }
}
