package com.kotlinservice.content.controller

import com.github.michaelbull.result.getOrThrow
import com.github.michaelbull.result.map
import com.kotlinservice.content.dto.DisneyApiPaginatedResponse
import com.kotlinservice.content.service.DisneyApiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.Disposable
import reactor.core.publisher.Mono


@RestController
@RequestMapping("v1/disneyapi")
class DisneyApiController (val disneyApiService: DisneyApiService) {

    @GetMapping("/sync")
    fun syncInfo (): ResponseEntity<Disposable> {
        return disneyApiService.retrieveCharacterData()
            .map {
                ResponseEntity.ok(it)
            }
            .getOrThrow{
                ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR)
            }
    }
}