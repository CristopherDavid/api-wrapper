package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class ShortFilm(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String = "",

    @ManyToMany(mappedBy = "shortFilms")
    val characters: List<Character>? = null
)
