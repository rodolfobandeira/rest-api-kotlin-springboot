package com.rodolfobandeira.forum.dto

import com.rodolfobandeira.forum.model.StatusTopic
import java.time.LocalDateTime

data class TopicView (
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val createdAt: LocalDateTime
)
