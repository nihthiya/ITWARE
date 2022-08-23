package com.example.itware.ui.movies.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itware.R
import com.example.itware.data.model.request.MoviesFavRequest
import com.example.itware.data.model.request.MoviesRequest
import com.example.itware.data.model.response.DataItem
import com.example.itware.data.model.response.FailureResponse
import com.example.itware.data.repository.MoviesRepository
import com.example.itware.utils.Application.Companion.isConnected
import com.example.itware.utils.ErrorResponseUtils
import com.example.itware.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    val movies = MutableLiveData<List<DataItem?>?>()
    val errorResponse = MutableLiveData<FailureResponse>()

    fun getAllMovies(context: Context,userName: String) {
        when {
            isConnected() -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            moviesRepository.getMovies(MoviesRequest(userName)).let {
                                if (it.isSuccessful) {
                                    movies.postValue(it.body()?.data)
                                }
                            }

                        } catch (throwable: Throwable) {
                            ErrorResponseUtils.error(throwable) {
                                errorResponse.postValue(it)
                            }
                        }

                    }

                }
            }
            else -> Utils.showToast(context,context.resources.getString(R.string.connection))

        }
    }

    fun addFavMovies(context: Context,userName: String,moviesId: String) {
        when {
            isConnected() -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            moviesRepository.addFavMovies(MoviesFavRequest(userName,moviesId.toInt())).let {
                            }

                        } catch (throwable: Throwable) {
                            ErrorResponseUtils.error(throwable) {
                                errorResponse.postValue(it)
                            }
                        }

                    }

                }
            }
            else -> Utils.showToast(context,context.resources.getString(R.string.connection))
        }
    }
}