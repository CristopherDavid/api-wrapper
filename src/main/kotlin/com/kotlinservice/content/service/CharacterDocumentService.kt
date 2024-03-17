package com.kotlinservice.content.service

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.entity.*
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.mappers.CharacterResponseMappers
import com.kotlinservice.content.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
open class CharacterDocumentService(
    private val characterMongoRepository: CharacterMongoRepository,
    private val characterRepository: CharacterRepository,
    private val filmRepository: FilmRepository,
    private val shortFilmRepository: ShortFilmRepository,
    private val tvShowRepository: TvShowRepository,
    private val parkAttractionRepository: ParkAttractionRepository,
    private val videoGameRepository: VideoGameRepository,
) {

    fun retrieveAllDocuments(): Result<List<CharactersDocumentResponse>, DatabaseError> {
        return runCatching {
            val documents = characterMongoRepository.findAll()
            documents.map {
                CharacterResponseMappers.toCharactersDocumentResponse(it)
            }
        }
            .mapError {
                DatabaseError(it.message)
            }

    }

    fun retrieveAllFilms(): Result<List<String>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllFilms()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.films }.distinct()
            }
    }

    fun retrieveAndProcessFilms(): Result<List<Film>, DatabaseError> {
        return retrieveAllFilms()
            .map {
                it.map { name ->
                    filmRepository.save(
                        Film(
                            name = name
                        )
                    )
                }
            }
    }

    fun retrieveAndProcessVideogames(): Result<List<Videogame>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllVideoGames()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.videoGames }.distinct()
            }
            .map {
                it.map { game ->
                    videoGameRepository.save(
                        Videogame(
                            name = game
                        )
                    )
                }
            }
    }

    fun retrieveAndProcessParkAttractions(): Result<List<ParkAttraction>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllParkAttractions()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.parkAttractions }.distinct()
            }
            .map {
                it.map { name ->
                    parkAttractionRepository.save(
                        ParkAttraction(
                            name = name
                        )
                    )
                }
            }
    }

    fun retrieveAndProcessShortFilms(): Result<List<ShortFilm>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllShortFilms()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.shortFilms }.distinct()
            }
            .map {
                it.map { name ->
                    shortFilmRepository.save(
                        ShortFilm(
                            name = name
                        )
                    )
                }
            }
    }

    fun retrieveAndProcessTvShows(): Result<List<TvShow>, DatabaseError> {

        return runCatching {
            characterMongoRepository.findAllTvShows()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map {
                it.flatMap { it.tvShows }.distinct()
            }
            .map {
                it.map { name ->
                    tvShowRepository.save(
                        TvShow(
                            name = name
                        )
                    )
                }
            }
    }

    @Transactional
    open fun retrieveAndProcessCharacters(): Result<List<Character>, DatabaseError> {
        return runCatching {
            characterMongoRepository.findAll()
        }
            .mapError {
                DatabaseError(it.message)
            }
            .map { characters ->
                characters.map {
                    processAndSaveCharacter(it)
                }
            }
    }


    private fun processAndSaveCharacter(characterDocument: CharacterResponse): Character {


        val characterFilms = characterDocument.films.mapNotNull {
            filmRepository.findByName(it)
        }.toMutableList()

        val characterTvShows = characterDocument.tvShows.mapNotNull {
            tvShowRepository.findByName(it)
        }.toMutableList()

        val characterShortFilms = characterDocument.shortFilms.mapNotNull {
            shortFilmRepository.findByName(it)
        }.toMutableList()

        val characterParkAttractions = characterDocument.parkAttractions.mapNotNull {
            parkAttractionRepository.findByName(it)

        }.toMutableList()

        val characterVideoGames = characterDocument.videoGames.mapNotNull {
            videoGameRepository.findByName(it)
        }.toMutableList()

        val character = Character(
            name = characterDocument.name,
            sourceUrl = characterDocument.sourceUrl,
            imageUrl = characterDocument.imageUrl,
            films = characterFilms,
            tvShows = characterTvShows,
            shortFilms = characterShortFilms,
            parkAttractions = characterParkAttractions,
            videogames = characterVideoGames

        )

        return characterRepository.save(character)
    }


}