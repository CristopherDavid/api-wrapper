package com.kotlinservice.content.entity

import jakarta.persistence.*


@Entity
data class Film (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String = "",

    @ManyToMany(mappedBy = "films")
    val characters: MutableList<Character> = mutableListOf()
)