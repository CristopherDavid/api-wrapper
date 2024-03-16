package com.kotlinservice.content

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CourseKotlinServiceApplication

fun main(args: Array<String>) {
    runApplication<CourseKotlinServiceApplication>(*args)
}