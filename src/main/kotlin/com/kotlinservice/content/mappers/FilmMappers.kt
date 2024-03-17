package com.kotlinservice.content.mappers

import com.kotlinservice.content.dto.FilmDto
import com.kotlinservice.content.entity.Film

object FilmMappers {

    fun filmEntityToFilmDto(film: Film): FilmDto{
        return FilmDto(
            name = film.name,
            characters = film.characters.map {it.name}
        )
    }
}