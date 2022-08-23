package com.example.itware.data.model.response

import com.google.gson.annotations.SerializedName

data class MoviesFavResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null
)
