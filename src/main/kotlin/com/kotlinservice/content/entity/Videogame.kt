package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class Videogame(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String = "",

    @ManyToMany(mappedBy = "videogames")
    val characters: List<Character>? = null
)

