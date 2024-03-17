package com.kotlinservice.content.controller

import com.github.michaelbull.result.getOrThrow
import com.github.michaelbull.result.map
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.entity.Film
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
    @GetMapping("/process/films")
    fun processAllFilms(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessFilms()
            .map {
                ResponseEntity.ok("${it.size} films, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/process/videogames")
    fun processAllVideogames(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessVideogames()
            .map {
                ResponseEntity.ok("${it.size} videogames, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/process/parkAttractions")
    fun processAllParkAttractions(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessParkAttractions()
            .map {
                ResponseEntity.ok("${it.size} park attractions, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/process/tvShows")
    fun processAllTvShows(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessTvShows()
            .map {
                ResponseEntity.ok("${it.size} Tv shows, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/process/shortFilms")
    fun processAllShortFilms(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessShortFilms()
            .map {
                ResponseEntity.ok("${it.size} short films, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

    @GetMapping("/process/characters")
    fun processAllCharacters(): ResponseEntity<String> {
        return characterDocumentService.retrieveAndProcessCharacters()
            .map {
                ResponseEntity.ok("${it.size} characters, inserted into the DB")
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }

}