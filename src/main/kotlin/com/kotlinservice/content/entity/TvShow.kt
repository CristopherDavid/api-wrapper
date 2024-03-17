package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class TvShow (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String = "",

    @ManyToMany(mappedBy = "tvShows")
    val characters: List<Character>? = null
)
