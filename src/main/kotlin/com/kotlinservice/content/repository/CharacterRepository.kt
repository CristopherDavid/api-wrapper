package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.Character
import org.springframework.data.repository.CrudRepository

interface CharacterRepository : CrudRepository<Character, Int> {
    fun findByName(name: String): Character?
}