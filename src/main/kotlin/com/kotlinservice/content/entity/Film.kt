package com.kotlinservice.content.entity

import jakarta.persistence.*


@Entity
data class Film (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "film_character",
        joinColumns = [JoinColumn(name = "film_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")]
    )
    val characters: MutableList<Character> = mutableListOf()
)