package com.funcode.funmovie.favorite.di


import android.content.Context
import com.funcode.funmovie.di.CoreComponent
import com.funcode.funmovie.favorite.movie.FavoriteMovieFragment
import com.funcode.funmovie.favorite.tvshow.FavoriteTvShowFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FavoriteModule::class, ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }

    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)
}