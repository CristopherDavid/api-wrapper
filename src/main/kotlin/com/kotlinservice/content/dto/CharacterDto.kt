package com.kotlinservice.content.dto

data class CharacterDto(
    val name: String,
    val sourceUrl : String,
    val imageUrl : String,

    val films: List<String>,
    val parkAttractions: List <String>,
    val videogames : List<String>,
    val tvShows: List<String>,
    val shortFilms: List<String>
)
