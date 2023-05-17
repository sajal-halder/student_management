package com.sajal.student_management.repository;

import com.sajal.student_management.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,String> {
}
