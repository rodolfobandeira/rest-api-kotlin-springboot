package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.model.Course
import com.rodolfobandeira.forum.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {
    fun findById(id: Long): Course {
        return repository.getById(id)
    }
}
