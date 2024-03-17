package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class TvShow (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tv-show_character",
        joinColumns = [JoinColumn(name = "tv-show_id")],
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