package com.funcode.core.domain.repository

import com.funcode.core.data.Resource
import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface ImpleMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Film>>>
    fun getAllTvShow(): Flow<Resource<List<Film>>>
    fun getAllMovieFavorite(): Flow<List<Film>>
    fun getAllTvShowFavorite(): Flow<List<Film>>
    fun getFilm(id: Int): Flow<Film>
    fun setFilmFavorited(film: Film)

}