package com.kotlinservice.content.dto

data class DisneyApiPaginatedResponse(
    val info: InfoObject,
    val data: List<CharactersApiResponse>
)

data class InfoObject (
    val count: Int,
    val totalPages: Int,
    val previousPage: String?,
    val nextPage: String?
)