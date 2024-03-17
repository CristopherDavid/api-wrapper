package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class ParkAttraction(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String ="",

    @ManyToMany(mappedBy = "parkAttractions")
    val characters: List<Character>? = null
)
