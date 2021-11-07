package com.rodolfobandeira.forum.repository

import com.rodolfobandeira.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(username: String?): User?
    // The magic happens here thanks to JapRepository!

}