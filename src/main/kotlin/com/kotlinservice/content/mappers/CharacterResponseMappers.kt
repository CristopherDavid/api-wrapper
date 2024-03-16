package com.kotlinservice.content.mappers

import com.kotlinservice.content.dto.CharactersApiResponse
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.entity.CharacterResponse

object CharacterResponseMappers {

    fun fromCharactersApiResponse(charactersApiResponse: CharactersApiResponse): CharacterResponse{
        return CharacterResponse (
            id = charactersApiResponse.id,
            name = charactersApiResponse.name,
            imageUrl = charactersApiResponse.imageUrl,
            url = charactersApiResponse.url,
            allies = charactersApiResponse.allies,
            enemies = charactersApiResponse.enemies,
            films = charactersApiResponse.films,
            shortFilms = charactersApiResponse.shortFilms,
            parkAttractions = charactersApiResponse.parkAttractions,
            sourceUrl = charactersApiResponse.sourceUrl,
            tvShows = charactersApiResponse.tvShows,
            videoGames = charactersApiResponse.videoGames
        )
    }

    fun toCharactersDocumentResponse(characterResponse: CharacterResponse): CharactersDocumentResponse{
        return CharactersDocumentResponse(
            id = characterResponse.id,
            name = characterResponse.name
        )
    }
}