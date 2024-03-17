package com.kotlinservice.content.service

import com.github.michaelbull.result.*
import com.kotlinservice.content.dto.CharactersDocumentResponse
import com.kotlinservice.content.entity.*
import com.kotlinservice.content.errors.DatabaseError
import com.kotlinservice.content.mappers.CharacterResponseMappers
import com.kotlinservice.content.repository.*
import org.springframework.stereotype.Service


@Service
class CharacterDocumentService(
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

    fun retrieveAndProcessCharacters(): Result<List<Character>, DatabaseError> {
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
        val character = Character(
            name = characterDocument.name,
            sourceUrl = characterDocument.sourceUrl,
            imageUrl = characterDocument.imageUrl
        )

        characterDocument.films.forEach {
            val film = filmRepository.findByName(it)
            film?.let {
                character.films.add(it)
            }
        }

        characterDocument.tvShows.forEach {
            val tvShow = tvShowRepository.findByName(it)
            tvShow?.let {
                character.tvShows.add(it)
            }
        }

        characterDocument.shortFilms.forEach {
            val shortFilm = shortFilmRepository.findByName(it)
            shortFilm?.let {
                character.shortFilms.add(it)
            }
        }

        characterDocument.parkAttractions.forEach {
            val film = parkAttractionRepository.findByName(it)
            film?.let {
                character.parkAttractions.add(it)
            }
        }

        characterDocument.videoGames.forEach {
            val film = videoGameRepository.findByName(it)
            film?.let {
                character.videogames.add(it)
            }
        }

        return characterRepository.save(character)
    }


}