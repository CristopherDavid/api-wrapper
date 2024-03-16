package com.kotlinservice.content.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.mappers.CharacterResponseMappers
import com.kotlinservice.content.repository.CharacterMongoRepository
import org.springframework.stereotype.Service


@Service
class CharacterDocumentService(
    private val characterMongoRepository: CharacterMongoRepository,
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