package com.example.itware.data.model.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("movie_name")
	val movieName: String? = null,

	@field:SerializedName("movie_id")
	val movieId: Int? = null,

	@field:SerializedName("status")
	var status: Int? = null
)
