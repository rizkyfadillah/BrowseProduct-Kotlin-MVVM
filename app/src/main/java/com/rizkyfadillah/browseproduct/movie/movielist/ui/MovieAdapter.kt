package com.rizkyfadillah.browseproduct.movie.movielist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.movie.common.model.Movie
import kotlinx.android.synthetic.main.layout_item_movie.view.*

/**
 * Created by Rizky on 19/04/18.
 */

class MovieAdapter(val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                    .load("http://image.tmdb.org/t/p/w185" + movie.posterPath)
                    .into(itemView.imageview)
        }

    }

}