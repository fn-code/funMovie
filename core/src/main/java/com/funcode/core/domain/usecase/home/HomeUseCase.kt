package com.funcode.core.domain.usecase.home

import com.funcode.core.data.Resource
import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getAllMovie(): Flow<Resource<List<Film>>>
    fun getAllTvShow(): Flow<Resource<List<Film>>>
    fun setFilmFavorite(film: Film)
}