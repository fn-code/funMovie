package com.funcode.core.domain.usecase.home

import com.funcode.core.data.MovieRepository
import com.funcode.core.data.Resource
import com.funcode.core.domain.model.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val movieRepository: MovieRepository): HomeUseCase {
    override fun getAllMovie(): Flow<Resource<List<Film>>> {
        return movieRepository.getAllMovie()
    }

    override fun getAllTvShow(): Flow<Resource<List<Film>>> {
        return movieRepository.getAllTvShow()
    }

    override fun setFilmFavorite(film: Film) {
        movieRepository.setFilmFavorited(film)
    }
}