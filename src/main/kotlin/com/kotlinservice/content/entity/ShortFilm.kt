package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class ShortFilm(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "short-film_character",
        joinColumns = [JoinColumn(name = "short-film_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")]
    )
    val characters: List<Character>? = null
)
