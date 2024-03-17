package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class Character(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,
    val name: String = "",
    val imageUrl: String? = null,
    val sourceUrl: String = "",

    @ManyToMany(mappedBy = "characters")
    val films: MutableList<Film> = mutableListOf(),

    @ManyToMany(mappedBy = "characters")
    val shortFilms: MutableList<ShortFilm> = mutableListOf(),

    @ManyToMany(mappedBy = "characters")
    val videogames: MutableList<Videogame> = mutableListOf(),

    @ManyToMany(mappedBy = "characters")
    val tvShows: MutableList<TvShow> = mutableListOf(),

    @ManyToMany(mappedBy = "characters")
    val parkAttractions: MutableList<ParkAttraction> = mutableListOf()
) {
    constructor(name: String, sourceUrl: String) : this(
        id = null,
        name = name,
        sourceUrl = sourceUrl,
        imageUrl = null,
        films = mutableListOf(),
        shortFilms = mutableListOf(),
        videogames = mutableListOf(),
        tvShows = mutableListOf(),
        parkAttractions = mutableListOf()
    ) {

    }
}