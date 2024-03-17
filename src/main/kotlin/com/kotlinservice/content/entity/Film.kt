package com.kotlinservice.content.entity

import jakarta.persistence.*


@Entity
data class Film (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,
    var name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "film_character",
        joinColumns = [JoinColumn(name = "film_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")]
    )
    val characters: List<Character>? = null
) {
    constructor(name: String) : this(
        id= null,
        name = name,
        characters = null
    )
}