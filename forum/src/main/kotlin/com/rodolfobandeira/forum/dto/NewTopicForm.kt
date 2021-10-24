package com.rodolfobandeira.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm (
    @field:NotEmpty(message = "Title can't be blank")
    @field:Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    val title: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 1025)
    val message: String,

    @field:NotNull
    val courseId: Long,

    @field:NotNull
    val authorId: Long
)
