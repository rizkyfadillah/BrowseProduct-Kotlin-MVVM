package com.rizkyfadillah.browseproduct.movie.moviedetail.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rizkyfadillah.browseproduct.BrowseProductApp
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.movie.common.di.MovieModule
import com.rizkyfadillah.browseproduct.movie.common.model.Review
import com.rizkyfadillah.browseproduct.movie.movielist.ui.MovieReviewAdapter
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_POSTER_PATH = "poster_path"
        val EXTRA_BACKDROP_PATH = "backdrop_path"
        val EXTRA_OVERVIEW = "overview"
        val EXTRA_ORIGINAL_TITLE = "original_title"
        val EXTRA_RELEASE_DATE = "release_date"
        val EXTRA_VOTE_AVERAGE = "vote_average"
        val EXTRA_VOTE_COUNT = "vote_count"
        val EXTRA_ID = "id"
    }

    @Inject lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val app = application as BrowseProductApp
        app.appComponent.plus(MovieModule())
                .inject(this)

        val posterPath = intent.getStringExtra(EXTRA_POSTER_PATH)
        val backdropPath = intent.getStringExtra(EXTRA_BACKDROP_PATH)
        val overview = intent.getStringExtra(EXTRA_OVERVIEW)
        val originalTitle = intent.getStringExtra(EXTRA_ORIGINAL_TITLE)
        val releaseDate = intent.getStringExtra(EXTRA_RELEASE_DATE)
        val voteAverage = intent.getDoubleExtra(EXTRA_VOTE_AVERAGE, 0.0)
        val voteCount = intent.getIntExtra(EXTRA_VOTE_COUNT, 0)
        val id = intent.getStringExtra(EXTRA_ID)

        text_synopsis.text = overview
        text_title.text = originalTitle
        text_rating.text = voteAverage.toString()
        text_vote_count.text = voteCount.toString()
        text_release_date.text = releaseDate

        val movieReviews = ArrayList<Review>()
        val movieReviewAdapter = MovieReviewAdapter(movieReviews)

        recyclerview_review.layoutManager = LinearLayoutManager(this)
        recyclerview_review.adapter = movieReviewAdapter

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780" + posterPath)
                .into(poster_image)

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780" + backdropPath)
                .into(backdrop)

        movieDetailViewModel.getMovieReviews(id)
                .subscribe({
                    movieReviews.clear()
                    movieReviews.addAll(it.data!!)
                    movieReviewAdapter.notifyDataSetChanged()
                })

        movieDetailViewModel.getMovieVideos(id)
                .subscribe({

                })
    }

}