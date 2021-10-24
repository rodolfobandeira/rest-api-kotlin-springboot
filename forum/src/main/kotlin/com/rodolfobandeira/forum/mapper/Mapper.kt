package com.rodolfobandeira.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U

}
