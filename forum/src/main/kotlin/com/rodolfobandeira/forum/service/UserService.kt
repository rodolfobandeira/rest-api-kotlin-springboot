package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.model.User
import com.rodolfobandeira.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
        fun findById(id: Long): User {
            return repository.getById(id)
        }
    }
