package com.kotlinservice.content.service

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kotlinservice.content.entity.Film
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.repository.FilmRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class FilmService (private val filmRepository: FilmRepository) {

    @Transactional
    open fun getFilm(name: String): Result<Film, DatabaseError> {

        val film = filmRepository.findByName(name)

        return if (film != null) {
            Ok(film)
        } else {
            Err(DatabaseError("Not found"))
        }
    }
}