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

    @ManyToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_film",
        joinColumns = [JoinColumn(name = "character_id")],
        inverseJoinColumns = [JoinColumn(name = "film_id")]
    )
    val films: MutableList<Film> = mutableListOf(),

    @ManyToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_shortFilm",
        joinColumns = [JoinColumn(name = "character_id")],
        inverseJoinColumns = [JoinColumn(name = "shortFilm_id")]
    )
    val shortFilms: MutableList<ShortFilm> = mutableListOf(),

    @ManyToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_videogames",
        joinColumns = [JoinColumn(name = "character_id")],
        inverseJoinColumns = [JoinColumn(name = "videogame_id")]
    )
    val videogames: MutableList<Videogame> = mutableListOf(),

    @ManyToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_tvShow",
        joinColumns = [JoinColumn(name = "character_id")],
        inverseJoinColumns = [JoinColumn(name = "tvShow_id")]
    )
    val tvShows: MutableList<TvShow> = mutableListOf(),

    @ManyToMany(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE), fetch = FetchType.LAZY)
    @JoinTable(
        name = "character_parkAttractions",
        joinColumns = [JoinColumn(name = "character_id")],
        inverseJoinColumns = [JoinColumn(name = "parkAttraction_id")]
    )
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