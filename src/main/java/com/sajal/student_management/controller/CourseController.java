package com.sajal.student_management.controller;

import com.sajal.student_management.entity.Courses;
import com.sajal.student_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<Object> getCourseById(@RequestParam String courseId){
        try {
           return ResponseEntity.ok(courseService.getCourseById(courseId));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> gelAllCourses(){
        try {
            return ResponseEntity.ok(courseService.getAllCourses());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Object>addCourse(@RequestBody Courses courses){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courses));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object>deleteCourseById(@RequestParam String courseId){
        try {
            courseService.deleteCourseById(courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Deleted");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
