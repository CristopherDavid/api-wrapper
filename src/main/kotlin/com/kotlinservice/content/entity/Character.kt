package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String,
    val imageUrl: String? = null,
    val sourceUrl: String,

    @ManyToMany(mappedBy = "characters")
    val films: List<Film>? = null,

    @ManyToMany(mappedBy = "characters")
    val shortFilms: List<ShortFilm>? = null,

    @ManyToMany(mappedBy = "characters")
    val videogames: List<Videogame>? = null,

    @ManyToMany(mappedBy = "characters")
    val tvShows: List<TvShow>? = null,

    @ManyToMany(mappedBy = "characters")
    val parkAttractions: List<ParkAttraction>? = null
) {
    constructor(name: String, sourceUrl: String) : this(
        id = null,
        name = name,
        sourceUrl = sourceUrl,
        imageUrl = null,
        films = null,
        shortFilms = null,
        videogames = null,
        tvShows = null,
        parkAttractions = null
    ) {

    }
}