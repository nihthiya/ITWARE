package com.example.itware.ui.login.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itware.R
import com.example.itware.data.model.request.UserLoginRequest
import com.example.itware.data.model.response.FailureResponse
import com.example.itware.data.model.response.UserLoginResponse
import com.example.itware.data.repository.UserLoginRepository
import com.example.itware.utils.Application.Companion.isConnected
import com.example.itware.utils.ErrorResponseUtils
import com.example.itware.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val userLoginRepository: UserLoginRepository
) : ViewModel() {
    val loginData = MutableLiveData<UserLoginResponse>()
    private val errorResponse = MutableLiveData<FailureResponse>()

    fun postUserDetails(context: Context, userName: String, userPassword: String) {
        when {
            isConnected() -> {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            userLoginRepository.getUserLogin(UserLoginRequest(userName,userPassword)).let {
                                    loginData.postValue(it.body())

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