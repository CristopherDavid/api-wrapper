package com.kotlinservice.content.mappers

import com.kotlinservice.content.dto.CharacterDto
import com.kotlinservice.content.entity.Character

object CharacterMappers {

    fun characterEntityToCharacterDto(character: Character): CharacterDto {
        return CharacterDto(
            name = character.name,
            sourceUrl = character.sourceUrl,
            imageUrl = character.imageUrl ?: "",
            films = character.films.map { it.name },
            parkAttractions = character.parkAttractions.map { it.name },
            shortFilms = character.shortFilms.map { it.name },
            tvShows = character.tvShows.map { it.name },
            videogames = character.videogames.map { it.name }
        )
    }
}