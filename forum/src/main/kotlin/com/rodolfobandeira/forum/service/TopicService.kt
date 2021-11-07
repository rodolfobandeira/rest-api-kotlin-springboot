package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.dto.NewTopicForm
import com.rodolfobandeira.forum.dto.TopicView
import com.rodolfobandeira.forum.dto.TopicsByCategoryDto
import com.rodolfobandeira.forum.dto.UpdateTopicForm
import com.rodolfobandeira.forum.exception.NotFoundException
import com.rodolfobandeira.forum.mapper.TopicFormMapper
import com.rodolfobandeira.forum.mapper.TopicViewMapper
import com.rodolfobandeira.forum.model.Topic
import com.rodolfobandeira.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.persistence.EntityManager

@Service
class TopicService(
    private var repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found",
    private val em: EntityManager
) {

    fun list(
        courseName: String?,
        pagination: Pageable
    ): Page<TopicView> {
        print(em)
        val topics = if (courseName == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }

        return topics.map { t ->
            topicViewMapper.map(t)
        }
    }

    fun findById(id: Long): TopicView {
        val topic = repository.findById(id)
                .orElseThrow{NotFoundException(notFoundMessage)}

        return topicViewMapper.map(topic)
    }

    fun create(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic = repository.findById(form.id)
                .orElseThrow{NotFoundException(notFoundMessage)}

        topic.title = form.title
        topic.message = form.message

        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun report(): List<TopicsByCategoryDto> {
        return repository.report()
    }
}