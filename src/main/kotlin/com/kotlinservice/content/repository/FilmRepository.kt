package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.Film
import org.springframework.data.repository.CrudRepository

interface FilmRepository: CrudRepository<Film, Int> {
}