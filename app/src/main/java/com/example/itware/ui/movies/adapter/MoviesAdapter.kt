package com.example.itware.ui.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.itware.R
import com.example.itware.data.model.response.DataItem
import com.example.itware.databinding.ItemMovieBinding

typealias onLoadFav = (movieId: String) -> Unit

class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movieList: List<DataItem?>? = null
    private lateinit var context: Context
    private lateinit var onloadFav: onLoadFav

    constructor(
        context: Context,
        movieList: List<DataItem?>?,
        onloadFav: onLoadFav) : this() {
        this.context = context
        this.movieList = movieList
        this.onloadFav = onloadFav
    }

    override fun getItemCount() = movieList!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList?.get(position)
        try {
            holder.itemMovieBinding.tvMovieName.text = movie!!.movieName
            if (movie.status == 1) {
                holder.itemMovieBinding.ibFav.background = ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_border_24)
            } else if (movie.status == 2) {
                holder.itemMovieBinding.ibFav.background = ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24)
            }
            holder.itemMovieBinding.ibFav.setOnClickListener {
                onloadFav.invoke(movie.movieId.toString())
                if (movie.status == 1) {
                    holder.itemMovieBinding.ibFav.background = ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24)
                    movieList?.get(position)?.status = 2
                } else if (movie.status == 2) {
                    holder.itemMovieBinding.ibFav.background = ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_border_24)
                    movieList?.get(position)?.status = 1
                }
            }

        } catch (e: Exception) {

        }
    }


    inner class ViewHolder(
        val itemMovieBinding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(itemMovieBinding.root)

}