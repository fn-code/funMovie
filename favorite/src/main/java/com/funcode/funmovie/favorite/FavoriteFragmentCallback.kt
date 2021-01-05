package com.funcode.funmovie.favorite

import com.funcode.core.domain.model.Film


interface FavoriteFragmentCallback {
    fun onClick(film: Film)
    fun deleteFavorite(film: Film)
}