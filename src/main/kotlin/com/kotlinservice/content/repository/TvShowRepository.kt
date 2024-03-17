package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.TvShow
import org.springframework.data.repository.CrudRepository

interface TvShowRepository : CrudRepository<TvShow, Int> {

    fun findByName(name : String) : TvShow?
}