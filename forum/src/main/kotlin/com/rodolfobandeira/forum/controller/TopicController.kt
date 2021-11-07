package com.rodolfobandeira.forum.controller

import com.rodolfobandeira.forum.dto.NewTopicForm
import com.rodolfobandeira.forum.dto.TopicView
import com.rodolfobandeira.forum.dto.UpdateTopicForm
import com.rodolfobandeira.forum.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 4, sort = ["created_at"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicView> {
        // Receives "service" from the constructor in TopicController via dependency injection
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid form: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.create(form)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicView) // Return 201 with body object and location header
    }

    @PutMapping
    fun update(@RequestBody @Valid form: UpdateTopicForm): ResponseEntity<TopicView> {
        val topicView = service.update(form)

        return ResponseEntity.ok(topicView) // Return 200 with the object in the body
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 with empty body
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}