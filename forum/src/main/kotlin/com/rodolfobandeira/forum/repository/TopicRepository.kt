package com.rodolfobandeira.forum.repository

import com.rodolfobandeira.forum.dto.TopicsByCategoryDto
import com.rodolfobandeira.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new com.rodolfobandeira.forum.dto.TopicsByCategoryDto(course.category, COUNT(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicsByCategoryDto>
}