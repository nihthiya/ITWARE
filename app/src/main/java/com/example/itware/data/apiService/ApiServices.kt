package com.example.itware.data.apiService

import com.example.itware.data.model.request.MoviesFavRequest
import com.example.itware.data.model.request.MoviesRequest
import com.example.itware.data.model.request.UserLoginRequest
import com.example.itware.data.model.response.MoviesFavResponse
import com.example.itware.data.model.response.MoviesResponse
import com.example.itware.data.model.response.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/api/api/login/login")
    suspend fun loginUser(@Body userLoginRequest: UserLoginRequest): Response<UserLoginResponse>
}

interface MoviesAPI {
    @POST("/api/api/movies/movies")
    suspend fun getMovies(@Body moviesRequest: MoviesRequest): Response<MoviesResponse>

    @POST("/api/api/movies/modify_favourite")
    suspend fun addFavMovie(@Body moviesFavRequest: MoviesFavRequest): Response<MoviesFavResponse>
}

