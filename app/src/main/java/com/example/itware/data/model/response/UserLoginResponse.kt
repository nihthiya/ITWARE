package com.example.itware.data.model.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

	@field:SerializedName("messsage")
	val messsage: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)
