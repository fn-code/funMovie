package com.funcode.core.data.source.remote.network

import com.funcode.core.data.source.remote.response.MovieResponse
import com.funcode.core.data.source.remote.response.TVShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("movie/popular")
    suspend fun getAllMovie(
        @Query("page") page: Int,
        @Query("api_key") api: String
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getAllTvShow(
        @Query("page") page: Int,
        @Query("api_key") api: String
    ): TVShowResponse
}