package com.funcode.core.data.source.local

import com.funcode.core.data.source.local.entity.Film
import com.funcode.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mMovieDao: MovieDao) {

    fun getAllMovie(): Flow<List<Film>> = mMovieDao.getAllMovie()
    fun getAllTvShow(): Flow<List<Film>> = mMovieDao.getAllTvShow()
    fun getAllMovieFavorite(): Flow<List<Film>> = mMovieDao.getAllMovieFavorite()
    fun getAllTvShowFavorite(): Flow<List<Film>> = mMovieDao.getAllTvShowFavorite()
    fun getFilm(id: Int): Flow<Film> = mMovieDao.getModuleById(id)
    fun updateFilm(film: Film) = mMovieDao.updateFilm(film)
    suspend fun insertMovie(movies: List<Film>) {
        mMovieDao.insertMovies(movies)
    }
}
