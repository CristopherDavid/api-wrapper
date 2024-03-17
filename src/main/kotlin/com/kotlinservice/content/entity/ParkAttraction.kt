package com.kotlinservice.content.entity

import jakarta.persistence.*

@Entity
data class ParkAttraction(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? =  null ,

    val name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "park-attraction_character",
        joinColumns = [JoinColumn(name = "park-attraction_id")],
        inverseJoinColumns = [JoinColumn(name = "character_id")]
    )
    val characters: List<Character>? = null
){
    constructor(name: String) : this(
        id= null,
        name = name,
        characters = null
    )
}
