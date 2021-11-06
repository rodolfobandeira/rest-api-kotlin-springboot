package com.rodolfobandeira.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null, // "var" will allow us to modify the "id"
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: User,
    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList()
)