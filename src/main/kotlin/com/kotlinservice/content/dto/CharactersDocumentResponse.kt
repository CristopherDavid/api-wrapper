package com.kotlinservice.content.dto

data class CharactersDocumentResponse(
    val id: Int,
    val name: String,
)
data class CharacterFilms(val films: List<String>)
data class CharacterShortFilms(val shortFilms: List<String>)
data class CharacterVideoGames(val videoGames: List<String>)
data class CharacterParkAttractions(val parkAttractions: List<String>)
data class CharacterTvShows(val tvShows: List<String>)
data class CharacterEnemies(val enemies: List<String>)
data class CharacterAllies(val allies: List<String>)