package com.example.itware.data.network

import com.example.itware.data.model.request.MoviesFavRequest
import com.example.itware.data.model.request.MoviesRequest
import com.example.itware.data.model.request.UserLoginRequest
import com.example.itware.data.model.response.MoviesFavResponse
import com.example.itware.data.model.response.MoviesResponse
import com.example.itware.data.model.response.UserLoginResponse
import retrofit2.Response

interface UserLoginApiHelper {

    suspend fun getUserLoginAsync(userLoginRequest: UserLoginRequest): Response<UserLoginResponse>

}

interface MoviesApiHelper {

    suspend fun getMovies(moviesRequest: MoviesRequest): Response<MoviesResponse>

    suspend fun addFavMovies(moviesFavRequest: MoviesFavRequest): Response<MoviesFavResponse>

}
