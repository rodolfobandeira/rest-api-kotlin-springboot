package com.rodolfobandeira.forum.repository

import com.rodolfobandeira.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {
}