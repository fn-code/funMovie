package com.funcode.core.domain.usecase.detail

import com.funcode.core.data.MovieRepository
import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DetailInteractor @Inject constructor(private val movieRepository: MovieRepository): DetailUseCase {
    override fun getFilm(id: Int): Flow<Film> {
        return movieRepository.getFilm(id)
    }

    override fun setFilmFavorite(film: Film) {
        movieRepository.setFilmFavorited(film)
    }
}