package com.rizkyfadillah.popularmovies.movie.movielist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rizkyfadillah.popularmovies.R
import com.rizkyfadillah.popularmovies.movie.common.model.Review
import kotlinx.android.synthetic.main.item_movie_review.view.*

/**
 * Created by Rizky on 30/04/18.
 */

class MovieReviewAdapter(val movieReviews: List<Review>): RecyclerView.Adapter<MovieReviewAdapter.MovieReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_review, parent, false)

        return MovieReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieReviews.size
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.bind(movieReviews.get(position))
    }

    class MovieReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(review: Review) {
            itemView.text_author.text = review.author
            itemView.text_content.text = review.content
        }

    }

}