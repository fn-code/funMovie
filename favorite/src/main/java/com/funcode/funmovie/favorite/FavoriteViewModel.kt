package com.funcode.funmovie.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.usecase.favorite.FavoriteUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
    ): ViewModel() {

    fun getAllMovieFavorite(): LiveData<List<Film>> =
        favoriteUseCase.getMovieFavorite().asLiveData()

    fun getTvShowFavorite(): LiveData<List<Film>> =
        favoriteUseCase.getTvShowFavorite().asLiveData()

    fun removeFilmFavorite(filmVal: Film) {
        val filmData = Film(
            filmVal.id,
            filmVal.overview,
            filmVal.title,
            filmVal.posterPath,
            filmVal.backdropPath,
            filmVal.releaseDate,
            filmVal.voteAverage,
            filmVal.voteCount,
            filmVal.status,
            filmVal.jenis,
            2
        )
        favoriteUseCase.setFilmFavorite(filmData)
    }

}