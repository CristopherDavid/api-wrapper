package com.kotlinservice.content.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.michaelbull.result.*
import com.kotlinservice.content.dto.CharactersApiResponse
import com.kotlinservice.content.dto.DisneyApiPaginatedResponse
import com.kotlinservice.content.entity.CharacterResponse
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.errors.DatabaseTimeout
import com.kotlinservice.content.errors.DomainError
import com.kotlinservice.content.errors.ParsingError
import com.kotlinservice.content.mappers.CharacterResponseMappers
import com.kotlinservice.content.repository.CharacterMongoRepository
import org.springframework.http.codec.CodecConfigurer
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.Disposable
import reactor.core.publisher.Mono
import java.sql.SQLTimeoutException
import kotlin.reflect.KFunction1

@Service
class DisneyApiService(private val characterMongoRepository: CharacterMongoRepository) {
    private val baseUrl = "https://api.disneyapi.dev/character?pageSize=8500"
    private val webClient = WebClient.builder()
        .codecs { configurer: CodecConfigurer ->
            configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024) // 16MB
        }
        .baseUrl(baseUrl).build()
    private val objectMapper = ObjectMapper().registerKotlinModule()
    fun retrieveCharacterData(): Result<Disposable, DomainError> {
        return runCatching {
            webClient.get()
                .retrieve()
                .bodyToMono(String::class.java)
                .map { responseBody ->
                    objectMapper.readValue<DisneyApiPaginatedResponse>(responseBody)
                }
        }
            .mapError(
                ::exceptionToDomainMessage
            )
            .map {
                it.subscribe {
                    val characterResponses = it.data.map {
                        CharacterResponseMappers.fromCharactersApiResponse(it)
                    }
                    characterMongoRepository.insert(characterResponses)
                }
            }
    }

    private fun exceptionToDomainMessage(t: Throwable) = when (t) {
        is SQLTimeoutException -> DatabaseTimeout
        is NullPointerException -> ParsingError
        else -> DatabaseError(t.message)
    }
}