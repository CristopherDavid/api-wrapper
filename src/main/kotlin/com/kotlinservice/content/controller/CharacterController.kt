package com.kotlinservice.content.controller

import com.github.michaelbull.result.getOrThrow
import com.github.michaelbull.result.map
import com.kotlinservice.content.dto.CharacterDto
import com.kotlinservice.content.mappers.CharacterMappers
import com.kotlinservice.content.service.CharacterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/characters")
class CharacterController(private val characterService: CharacterService) {

    @GetMapping("/{name}")
    fun getCharacterByName(@PathVariable("name") name: String): ResponseEntity<CharacterDto> {
        return characterService.getCharacter(name).map {
            ResponseEntity.ok(CharacterMappers.characterEntityToCharacterDto(it))
        }.getOrThrow {
            print(it.toString())
            ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}