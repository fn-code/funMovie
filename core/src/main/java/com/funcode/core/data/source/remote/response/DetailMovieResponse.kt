package com.funcode.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("backdrop_path")
	val posterPath: String,

	@field:SerializedName("status")
	val status: String
)
