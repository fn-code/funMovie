package com.funcode.funmovie.di

import com.funcode.core.domain.usecase.detail.DetailInteractor
import com.funcode.core.domain.usecase.detail.DetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DetailModule {
    @Binds
    abstract fun provideDetailUseCase(detailInteractor: DetailInteractor): DetailUseCase
}