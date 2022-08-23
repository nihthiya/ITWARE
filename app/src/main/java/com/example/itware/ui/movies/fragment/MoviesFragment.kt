package com.example.itware.ui.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itware.R
import com.example.itware.databinding.FragmentMoviesBinding
import com.example.itware.ui.movies.adapter.MoviesAdapter
import com.example.itware.ui.movies.viewModel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MoviesFragment : Fragment() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesBinding: FragmentMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moviesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return moviesBinding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        moviesBinding.lifecycleOwner = this
        moviesBinding.moviesViewModel = moviesViewModel

        val userName = requireActivity().intent.getStringExtra("userName")!!


        moviesViewModel.getAllMovies(requireContext(),"althaf")


        moviesViewModel.movies.observe(this.viewLifecycleOwner){ moviesList ->
            moviesBinding.rvMovieList.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = MoviesAdapter(
                    requireContext(),
                    moviesList
                ) { movieId ->
                    moviesViewModel.addFavMovies(requireContext(), userName, movieId)
                }
            }

        }
    }

}