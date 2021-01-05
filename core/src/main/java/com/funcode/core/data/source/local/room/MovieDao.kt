package com.funcode.core.data.source.local.room

import androidx.room.*
import com.funcode.core.data.source.local.entity.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM film where jenis = 1 and favorite = 2")
    fun getAllMovie(): Flow<List<Film>>

    @Query("SELECT * FROM film where jenis = 2 and favorite = 2")
    fun getAllTvShow(): Flow<List<Film>>

    @Query("SELECT * FROM film where jenis = 1 and favorite = 1")
    fun getAllMovieFavorite(): Flow<List<Film>>

    @Query("SELECT * FROM film where jenis = 2 and favorite = 1")
    fun getAllTvShowFavorite(): Flow<List<Film>>

    @Query("SELECT * FROM film WHERE id = :filmid")
    fun getModuleById(filmid: Int): Flow<Film>

    @Update
    fun updateFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(module: List<Film>)
}