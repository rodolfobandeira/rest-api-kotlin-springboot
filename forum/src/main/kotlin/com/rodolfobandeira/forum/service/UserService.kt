package com.rodolfobandeira.forum.service

import com.rodolfobandeira.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(var users: List<User>) {

        init {
            val user = User(
                id = 1,
                name = "User 1",
                email = "email@example.com"
            )

            users = listOf(user)
        }

        fun findById(authorId: Long): User {
            return users.stream().filter { c ->
                c.id == authorId
            }.findFirst().get()
        }
    }
