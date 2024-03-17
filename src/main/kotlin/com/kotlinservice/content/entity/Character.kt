package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class Character (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,
    var name: String,

    @ManyToMany(mappedBy = "characters")
    val films: List<Film>? = null
)