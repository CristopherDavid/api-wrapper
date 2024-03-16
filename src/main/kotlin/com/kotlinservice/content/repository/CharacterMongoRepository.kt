package com.kotlinservice.content.repository

import com.kotlinservice.content.dto.*
import com.kotlinservice.content.entity.CharacterResponse
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CharacterMongoRepository: MongoRepository<CharacterResponse, String>{

    @Query(value = "{}", fields = "{films : 1, _id: 0}")
    fun findAllFilms(): List<CharacterFilms>

    @Query(value = "{}", fields = "{shortFilms : 1, _id: 0}")
    fun findAllShortFilms(): List<CharacterShortFilms>

    @Query(value = "{}", fields = "{tvShows : 1, _id: 0}")
    fun findAllTvShows(): List<CharacterTvShows>

    @Query(value = "{}", fields = "{videoGames : 1, _id: 0}")
    fun findAllVideoGames(): List<CharacterVideoGames>

    @Query(value = "{}", fields = "{parkAttractions : 1, _id: 0}")
    fun findAllParkAttractions(): List<CharacterParkAttractions>

    @Query(value = "{}", fields = "{enemies : 1, _id: 0}")
    fun findAllEnemies(): List<CharacterEnemies>

    @Query(value = "{}", fields = "{allies : 1, _id: 0}")
    fun findAllAllies(): List<CharacterAllies>

}
