package com.example.itware.di

import androidx.multidex.BuildConfig
import com.example.itware.data.apiService.LoginAPI
import com.example.itware.data.apiService.MoviesAPI
import com.example.itware.data.network.MoviesApiHelper
import com.example.itware.data.network.MoviesApiHelperImpl
import com.example.itware.data.network.UserLoginApiHelper
import com.example.itware.data.network.UserLoginApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "http://itware.fortiddns.com:444"

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideUserLoginApiService(retrofit: Retrofit): LoginAPI = retrofit.create(LoginAPI::class.java)

    @Provides
    @Singleton
    fun provideGetMoviesApiService(retrofit: Retrofit): MoviesAPI = retrofit.create(MoviesAPI::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: UserLoginApiHelperImpl): UserLoginApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideMoviesApiHelper(moviesApiHelper: MoviesApiHelperImpl): MoviesApiHelper = moviesApiHelper
}