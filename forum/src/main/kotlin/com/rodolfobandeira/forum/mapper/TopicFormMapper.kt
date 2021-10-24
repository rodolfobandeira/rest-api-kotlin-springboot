package com.rodolfobandeira.forum.mapper

import com.rodolfobandeira.forum.dto.NewTopicForm
import com.rodolfobandeira.forum.model.Topic
import com.rodolfobandeira.forum.service.CourseService
import com.rodolfobandeira.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private var courseService: CourseService,
    private var userService: UserService
) : Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.courseId),
            author = userService.findById(t.authorId)
        )
    }
}
