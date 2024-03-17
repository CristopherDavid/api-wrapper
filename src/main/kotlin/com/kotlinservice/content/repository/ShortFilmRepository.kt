package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.ShortFilm
import org.springframework.data.repository.CrudRepository

interface ShortFilmRepository : CrudRepository<ShortFilm, Int> {
}