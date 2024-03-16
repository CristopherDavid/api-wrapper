package com.kotlinservice.content.controller

import com.github.michaelbull.result.getOrThrow
import com.github.michaelbull.result.map
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.service.CharacterDocumentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/documents")
class CharacterDocumentController (private val characterDocumentService: CharacterDocumentService) {

    @GetMapping("/characters")
    fun getAllCharacterDocuments(): ResponseEntity<List<CharactersDocumentResponse>> {
        return characterDocumentService.retrieveAllDocuments()
            .map {
                ResponseEntity.ok(it)
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/films")
    fun getAllMovies(): ResponseEntity<List<String>> {
        return characterDocumentService.retrieveAllFilms()
            .map {
                ResponseEntity.ok(it)
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/videoGames")
    fun getAllVideoGames(): ResponseEntity<List<String>> {
        return characterDocumentService.retrieveAllVideoGames()
            .map {
                ResponseEntity.ok(it)
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/enemies")
    fun getAllEnemies(): ResponseEntity<List<String>> {
        return characterDocumentService.retrieveAllEnemies()
            .map {
                ResponseEntity.ok(it)
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }
}