package com.example.itware.data.repository

import com.example.itware.data.model.request.MoviesFavRequest
import com.example.itware.data.model.request.MoviesRequest
import com.example.itware.data.network.MoviesApiHelper
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiHelper: MoviesApiHelper) {
    suspend fun getMovies(moviesRequest: MoviesRequest) = apiHelper.getMovies(moviesRequest)
    suspend fun addFavMovies(moviesFavRequest: MoviesFavRequest) = apiHelper.addFavMovies(moviesFavRequest)
}