package com.funcode.funmovie.di

import com.funcode.core.data.MovieRepository
import com.funcode.core.data.source.local.room.MovieDao
import com.funcode.core.data.source.local.room.MovieDatabase
import com.funcode.core.data.source.remote.network.APIService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {

    fun provideDatabase(): MovieDatabase
    fun provideMovieDao(): MovieDao
    fun provideApiService(): APIService
    fun provideRepository() : MovieRepository
}