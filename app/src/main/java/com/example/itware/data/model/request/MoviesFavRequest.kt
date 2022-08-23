package com.example.itware.data.model.request

import com.google.gson.annotations.SerializedName

data class MoviesFavRequest(

	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("movie_id")
	val movieId: Int? = null
)
