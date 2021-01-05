package com.funcode.core.data

import com.funcode.core.data.source.local.LocalDataSource
import com.funcode.core.data.source.remote.RemoteDataSource
import com.funcode.core.data.source.remote.network.ApiResponse
import com.funcode.core.data.source.remote.response.Movie
import com.funcode.core.data.source.remote.response.TvShow
import com.funcode.core.domain.model.Film
import com.funcode.core.domain.repository.ImpleMovieRepository
import com.funcode.core.utils.AppExecutors
import com.funcode.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    ImpleMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Film>>> {
        return object : NetworkBoundResorce<List<Film>, List<Movie>>() {
            override fun loadFromDB(): Flow<List<Film>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapFilmEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<Movie>>> {
                return remoteDataSource.getAllMovie(1)
            }

            override suspend fun saveCallResult(data: List<Movie>?) {
                val filmList = DataMapper.mapMovieResponseToEntity(data)
                localDataSource.insertMovie(filmList)
            }
        }.asFlow()
    }

    override fun getAllTvShow(): Flow<Resource<List<Film>>> {
        return object : NetworkBoundResorce<List<Film>, List<TvShow>>() {
            public override fun loadFromDB(): Flow<List<Film>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapFilmEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Film>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShow>>> =
                remoteDataSource.getAllTvShow(1)

            override suspend fun saveCallResult(data: List<TvShow>?) {
                val filmList = DataMapper.mapTvShowResponseToEntity(data)
                localDataSource.insertMovie(filmList)
            }
        }.asFlow()
    }

    override fun getAllMovieFavorite(): Flow<List<Film>> {
        return localDataSource.getAllMovieFavorite().map {
            DataMapper.mapFilmEntitiesToDomain(it)
        }
    }

    override fun getAllTvShowFavorite(): Flow<List<Film>> {
        return localDataSource.getAllTvShowFavorite().map {
            DataMapper.mapFilmEntitiesToDomain(it)
        }
    }

    override fun getFilm(id: Int): Flow<Film> {
        return localDataSource.getFilm(id).map {
            DataMapper.mapFilmToDomain(it)
        }
    }

    override fun setFilmFavorited(film: Film) {
        val filmEntity = DataMapper.mapFilmToEntity(film)
        appExecutors.diskIO().execute { localDataSource.updateFilm(filmEntity) }
    }
}