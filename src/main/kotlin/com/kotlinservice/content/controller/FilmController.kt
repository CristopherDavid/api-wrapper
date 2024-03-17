package com.kotlinservice.content.controller

import com.github.michaelbull.result.getOrThrow
import com.github.michaelbull.result.map
import com.kotlinservice.content.dto.FilmDto
import com.kotlinservice.content.mappers.FilmMappers
import com.kotlinservice.content.service.FilmService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/films")
class FilmController(private val filmService: FilmService) {

    @GetMapping("/{name}")
    fun getFilmByName(@PathVariable("name") name: String): ResponseEntity<FilmDto> {
        return filmService.getFilm(name).map {
            ResponseEntity.ok(FilmMappers.filmEntityToFilmDto(it))
        }.getOrThrow {
            print(it.toString())
            ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}