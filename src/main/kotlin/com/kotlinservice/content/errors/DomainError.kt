package com.kotlinservice.content.errors

sealed interface DomainError

data object ParsingError : DomainError
data object MongoDBError : DomainError
data object DatabaseTimeout : DomainError
data class DatabaseError(val reason: String?) : DomainError