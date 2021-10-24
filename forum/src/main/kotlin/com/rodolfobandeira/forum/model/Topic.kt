package com.rodolfobandeira.forum.model

import java.time.LocalDateTime

data class Topic (
    var id: Long? = null, // "var" will allow us to modify the "id"
    val title: String,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    val answers: List<Answer> = ArrayList()
)