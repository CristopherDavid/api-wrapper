package com.kotlinservice.content.service

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kotlinservice.content.entity.Character
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.repository.CharacterRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class CharacterService(private val characterRepository: CharacterRepository) {

    @Transactional
    open fun getCharacter(name: String): Result<Character, DatabaseError> {

        val character = characterRepository.findByName(name)

        return if (character != null) {
            Ok(character)
        } else {
            Err(DatabaseError("Not found"))
        }
    }
}