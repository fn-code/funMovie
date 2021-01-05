package com.funcode.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvShow>
)
