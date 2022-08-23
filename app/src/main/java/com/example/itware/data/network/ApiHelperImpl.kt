package com.example.itware.data.network

import com.example.itware.data.apiService.LoginAPI
import com.example.itware.data.apiService.MoviesAPI
import com.example.itware.data.model.request.MoviesFavRequest
import com.example.itware.data.model.request.MoviesRequest
import com.example.itware.data.model.request.UserLoginRequest
import com.example.itware.data.model.response.MoviesFavResponse
import com.example.itware.data.model.response.MoviesResponse
import com.example.itware.data.model.response.UserLoginResponse
import retrofit2.Response
import javax.inject.Inject


class UserLoginApiHelperImpl @Inject constructor(private val userLoginAPI: LoginAPI) :
    UserLoginApiHelper {

    override suspend fun getUserLoginAsync(userLoginRequest: UserLoginRequest): Response<UserLoginResponse> =
        userLoginAPI.loginUser(userLoginRequest)
}


class MoviesApiHelperImpl @Inject constructor(private val moviesAPI: MoviesAPI) :
    MoviesApiHelper {

    override suspend fun getMovies(moviesRequest: MoviesRequest): Response<MoviesResponse> =
        moviesAPI.getMovies(moviesRequest)

    override suspend fun addFavMovies(moviesFavRequest: MoviesFavRequest): Response<MoviesFavResponse> =
        moviesAPI.addFavMovie(moviesFavRequest)

}
