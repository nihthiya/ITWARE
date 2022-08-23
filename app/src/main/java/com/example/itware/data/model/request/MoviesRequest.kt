package com.example.itware.data.model.request

import com.google.gson.annotations.SerializedName

data class MoviesRequest(

	@field:SerializedName("user_name")
	val userName: String? = null
)
