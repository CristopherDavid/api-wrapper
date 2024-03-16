package com.kotlinservice.content.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greeting")
class GreetingController {

    @GetMapping("/{name}")
    fun getGreeting (@PathVariable("name") name: String): String {
        return "Hello $name"
    }
}