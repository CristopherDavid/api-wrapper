package com.kotlinservice.content.service

import com.github.michaelbull.result.*
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.entity.Film
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.mappers.CharacterResponseMappers
import com.kotlinservice.content.repository.CharacterMongoRepository
import com.kotlinservice.content.repository.CharacterRepository
import com.kotlinservice.content.repository.FilmRepository
import org.springframework.stereotype.Service


@Service
class CharacterDocumentService(
    private val characterMongoRepository: CharacterMongoRepository,
    private val characterRepository: CharacterRepository,
    private val filmRepository: FilmRepository
) {

    fun retrieveAllDocuments(): Result<List<CharactersDocumentResponse>, DatabaseError> {
        return runCatching {
            val documents = characterMongoRepository.findAll()
            documents.map {
                CharacterResponseMappers.toCharactersDocumentResponse(it)
            }
        }
            .mapError {
                DatabaseError(it.message)
            }

    }

    fun retrieveAllFilms(): Result<List<String>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllFilms()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.films }.distinct()
            }
    }

    fun retrieveAndProcessFilms(): Result<List<Film>, DatabaseError> {
        return retrieveAllFilms()
            .map {
                it.map{film ->
                    filmRepository.save(Film(
                        name = film
                    ))
                }
            }
    }

    fun retrieveAllVideoGames(): Result<List<String>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllVideoGames()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.videoGames }.distinct()
            }
    }

    fun retrieveAllEnemies(): Result<List<String>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllEnemies()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.enemies }.distinct()
            }
    }
}