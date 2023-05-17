package com.sajal.student_management.service;

import com.sajal.student_management.entity.Courses;

import java.util.Set;

public interface CourseService {
    public Courses addCourse(Courses courses) throws Exception;
    public Courses getCourseById(String courseId) throws Exception;
    public void deleteCourseById(String courseId) throws Exception;

    public Set<Courses> getAllCourses();
}
