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

class MovieAdapter(private val listener: OnMovieItemClickListener, private val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface OnMovieItemClickListener {
        fun onClickMovie(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_movie, parent, false)

        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MovieViewHolder(itemView: View, val listener: OnMovieItemClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                    .load("http://image.tmdb.org/t/p/w185" + movie.posterPath)
                    .into(itemView.imageview)

            itemView.setOnClickListener {
                listener.onClickMovie(movie)
            }
        }

    }

}