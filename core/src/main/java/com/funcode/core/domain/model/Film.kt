package com.funcode.core.domain.model

data class Film(
    val id: Int,
    val overview: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val status: String?,
    val jenis: Int,
    val favorite: Int
)