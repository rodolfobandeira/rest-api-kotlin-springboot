package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.dto.NewTopicForm
import com.rodolfobandeira.forum.dto.TopicView
import com.rodolfobandeira.forum.dto.UpdateTopicForm
import com.rodolfobandeira.forum.exception.NotFoundException
import com.rodolfobandeira.forum.mapper.TopicFormMapper
import com.rodolfobandeira.forum.mapper.TopicViewMapper
import com.rodolfobandeira.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found"
) {

    fun list(): List<TopicView> {
        return topics.stream().map { t ->
            topicViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun findById(id: Long): TopicView {
        val topic = topics.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        return topicViewMapper.map(topic)
    }

    fun create(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        topic.id = topics.size.toLong() + 1
        topics = topics.plus(topic)

        return topicViewMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic = topics.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        val updatedTopic = Topic(
            id = form.id,
            title = form.title,
            message = form.message,
            createdAt = topic.createdAt,
            course = topic.course,
            author = topic.author,
            status = topic.status,
            answers = topic.answers
        )

        topics = topics.minus(topic).plus(
            updatedTopic
        )

        return topicViewMapper.map(updatedTopic)
    }

    fun delete(id: Long) {
        val topic = topics.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        topics = topics.minus(topic)
    }
}