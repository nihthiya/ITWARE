package com.example.itware.ui.movies.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.itware.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }
}