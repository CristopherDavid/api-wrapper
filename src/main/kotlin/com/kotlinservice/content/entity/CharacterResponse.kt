package com.kotlinservice.content.entity
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document
data class CharacterResponse(
    @Id val id: Int,
    val url: String,
    val imageUrl: String?,
    val name: String,
    val sourceUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>,
    val allies: List<String>,
    val enemies: List<String>
)
