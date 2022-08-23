package com.example.itware.data.repository

import com.example.itware.data.model.request.UserLoginRequest
import com.example.itware.data.network.UserLoginApiHelper
import javax.inject.Inject


class UserLoginRepository @Inject constructor(private val apiHelper: UserLoginApiHelper) {
    suspend fun getUserLogin(loginRequest: UserLoginRequest) = apiHelper.getUserLoginAsync(loginRequest)
}
