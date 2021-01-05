package com.funcode.funmovie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.usecase.detail.DetailUseCase

class DetailViewModel @ViewModelInject constructor(private val detailUseCase: DetailUseCase): ViewModel() {
    val filmID = MutableLiveData<Int>()

    fun setSelectedFilm(filmID: Int) {
        this.filmID.value = filmID
    }

    var film: LiveData<Film> = Transformations.switchMap(filmID) { mFilmID ->
        detailUseCase.getFilm(mFilmID).asLiveData()
    }

    fun setFavorited() {
        val filmVal = film.value
        if (filmVal != null) {
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
                if (filmVal.favorite == 1) 2 else 1
            )
            detailUseCase.setFilmFavorite(filmData)
        }
    }
}