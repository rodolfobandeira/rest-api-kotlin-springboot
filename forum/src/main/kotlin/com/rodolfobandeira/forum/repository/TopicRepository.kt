package com.rodolfobandeira.forum.repository

import com.rodolfobandeira.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
}