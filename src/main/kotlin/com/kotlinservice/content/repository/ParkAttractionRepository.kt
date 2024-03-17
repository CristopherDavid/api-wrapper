package com.kotlinservice.content.repository

import com.kotlinservice.content.entity.ParkAttraction
import org.springframework.data.repository.CrudRepository

interface ParkAttractionRepository : CrudRepository<ParkAttraction, Int> {

    fun findByName(name: String): ParkAttraction?
}