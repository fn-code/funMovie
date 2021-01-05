package com.funcode.funmovie.home.content

import com.funcode.core.domain.model.Film


interface FilmFragmentCallback {
    fun onClick(film: Film)
}