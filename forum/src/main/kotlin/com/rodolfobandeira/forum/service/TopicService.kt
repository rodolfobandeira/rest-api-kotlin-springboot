package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.dto.NewTopicForm
import com.rodolfobandeira.forum.dto.TopicView
import com.rodolfobandeira.forum.dto.UpdateTopicForm
import com.rodolfobandeira.forum.exception.NotFoundException
import com.rodolfobandeira.forum.mapper.TopicFormMapper
import com.rodolfobandeira.forum.mapper.TopicViewMapper
import com.rodolfobandeira.forum.model.Topic
import com.rodolfobandeira.forum.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private var repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found"
) {

    fun list(): List<TopicView> {
        return repository.findAll().stream().map { t ->
            topicViewMapper.map(t)
        }.collect(Collectors.toList())
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
}