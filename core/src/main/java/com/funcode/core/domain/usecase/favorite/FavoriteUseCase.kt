package com.funcode.core.domain.usecase.favorite

import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getMovieFavorite(): Flow<List<Film>>
    fun getTvShowFavorite(): Flow<List<Film>>
    fun setFilmFavorite(film: Film)
}