package com.funcode.core.utils

import com.funcode.core.data.source.local.entity.Film
import com.funcode.core.data.source.remote.response.Movie
import com.funcode.core.data.source.remote.response.TvShow

object DataMapper {
    fun mapFilmEntitiesToDomain(input: List<Film>): List<com.funcode.core.domain.model.Film> {
        val filmList = ArrayList<com.funcode.core.domain.model.Film>()
        input.map {
            val filmModel = com.funcode.core.domain.model.Film(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                status = it.status,
                jenis = it.jenis,
                favorite = it.favorite
            )
            filmList.add(filmModel)
        }
        return filmList
    }

    fun mapMovieResponseToEntity(input: List<Movie>?): List<Film> {
        val filmList = ArrayList<Film>()
        input?.map {
            val film = Film(
                it.id,
                it.overview,
                it.title,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.voteAverage,
                it.voteCount,
                it.status,
                1,
                2
            )
            filmList.add(film)
        }
        return filmList
    }

    fun mapTvShowResponseToEntity(input: List<TvShow>?): List<Film> {
        val filmList = ArrayList<Film>()
        input?.map {
            val film = Film(
                it.id,
                it.overview,
                it.name,
                it.posterPath,
                it.backdropPath,
                it.firstAirDate,
                it.voteAverage,
                it.voteCount,
                it.status,
                2,
                2
            )
            filmList.add(film)
        }
        return filmList
    }

    fun mapFilmToDomain(input: Film): com.funcode.core.domain.model.Film {
        return com.funcode.core.domain.model.Film(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            status = input.status,
            jenis = input.jenis,
            favorite = input.favorite
        )
    }

    fun mapFilmToEntity(input: com.funcode.core.domain.model.Film): Film {
        return Film(
            id = input.id,
            title = input.title,
            overview = input.overview,
            releaseDate = input.releaseDate,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            status = input.status,
            jenis = input.jenis,
            favorite = input.favorite
        )
    }
}