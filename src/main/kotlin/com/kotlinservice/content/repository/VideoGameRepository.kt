package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.Videogame
import org.springframework.data.repository.CrudRepository

interface VideoGameRepository: CrudRepository<Videogame, Int> {
}