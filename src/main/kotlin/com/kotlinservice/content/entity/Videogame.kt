package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class Videogame(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "videogame_character",
        joinColumns = [JoinColumn(name = "videogame_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")]
    )
    val characters: List<Character>? = null
)
{
    constructor(name: String) : this(
        id= null,
        name = name,
        characters = null
    )
}
