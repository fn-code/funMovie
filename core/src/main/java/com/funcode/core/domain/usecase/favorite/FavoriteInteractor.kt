package com.funcode.core.domain.usecase.favorite

import com.funcode.core.data.MovieRepository
import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(private val movieRepository: MovieRepository): FavoriteUseCase {
    override fun getMovieFavorite(): Flow<List<Film>> {
        return movieRepository.getAllMovieFavorite()
    }

    override fun getTvShowFavorite(): Flow<List<Film>> {
        return movieRepository.getAllTvShowFavorite()
    }

    override fun setFilmFavorite(film: Film) {
        movieRepository.setFilmFavorited(film)
    }
}