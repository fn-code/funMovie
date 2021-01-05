package com.funcode.core.domain.usecase.detail

import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun getFilm(id: Int): Flow<Film>
    fun setFilmFavorite(film: Film)
}