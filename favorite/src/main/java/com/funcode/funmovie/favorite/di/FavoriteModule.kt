package com.funcode.funmovie.favorite.di


import com.funcode.core.domain.usecase.favorite.FavoriteInteractor
import com.funcode.core.domain.usecase.favorite.FavoriteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun provideFavoriteUseCase(favoriteInteractor: FavoriteInteractor): FavoriteUseCase

}