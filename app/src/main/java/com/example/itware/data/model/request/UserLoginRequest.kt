package com.example.itware.data.model.request

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(



	@field:SerializedName("user_name")
	val userName: String? = null,

	@field:SerializedName("password")
	val password: String? = null
)
