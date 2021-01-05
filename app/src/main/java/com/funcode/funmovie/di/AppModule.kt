package com.funcode.funmovie.di

import com.funcode.core.domain.usecase.home.HomeInteractor
import com.funcode.core.domain.usecase.home.HomeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [DetailModule::class])
@InstallIn(ActivityComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideHomeUseCase(homeInteractor: HomeInteractor): HomeUseCase

}