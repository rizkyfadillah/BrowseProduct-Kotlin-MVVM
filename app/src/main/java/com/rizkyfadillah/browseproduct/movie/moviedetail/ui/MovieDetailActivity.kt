package com.rizkyfadillah.browseproduct.movie.moviedetail.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rizkyfadillah.browseproduct.BrowseProductApp
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.di.MovieModule
import com.rizkyfadillah.browseproduct.movie.common.model.Review
import com.rizkyfadillah.browseproduct.movie.common.viewmodel.MovieViewModelFactory
import com.rizkyfadillah.browseproduct.movie.movielist.ui.MovieReviewAdapter
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

/**
 * Created by Rizky on 04/06/18.
 */

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieReviewAdapter: MovieReviewAdapter
    private var movieReviews = ArrayList<Review>()

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieDetailViewModel: MovieDetailViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val app = applicationContext as BrowseProductApp
        app.appComponent.plus(MovieModule()).inject(this)

        movieDetailViewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)

        val posterPath = intent.getStringExtra(EXTRA_POSTER_PATH)
        val backdropPath = intent.getStringExtra(EXTRA_BACKDROP_PATH)
        val overview = intent.getStringExtra(EXTRA_OVERVIEW)
        val originalTitle = intent.getStringExtra(EXTRA_ORIGINAL_TITLE)
        val releaseDate = intent.getStringExtra(EXTRA_RELEASE_DATE)
        val voteAverage = intent.getDoubleExtra(EXTRA_VOTE_AVERAGE, 0.0)
        val voteCount = intent.getIntExtra(EXTRA_VOTE_COUNT, 0)
        val id = intent.getStringExtra(EXTRA_ID)

        setActionBarTitle(originalTitle)

        text_synopsis.text = overview
        text_title.text = originalTitle
        text_rating.text = voteAverage.toString()
        text_vote_count.text = voteCount.toString()
        text_release_date.text = releaseDate

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780$posterPath")
                .into(poster_image)

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780$backdropPath")
                .into(backdrop)

        movieReviews = ArrayList()
        movieReviewAdapter = MovieReviewAdapter(movieReviews)

        recyclerview_review.layoutManager = LinearLayoutManager(this)
        recyclerview_review.adapter = movieReviewAdapter

        showReviews(id)
    }

    private fun setActionBarTitle(originalTitle: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = originalTitle
    }

    private fun showReviews(id: String?) {
        if (id != null) {
            movieDetailViewModel.getMovieReviews(id)
                    .subscribe({
                        if (it.uiState == UIModel.UIState.LOADING) {

                        } else if (it.uiState == UIModel.UIState.ERROR) {

                        } else if (it.uiState == UIModel.UIState.SUCCESS) {
                            movieReviews.clear()
                            movieReviews.addAll(it.data!!)
                            movieReviewAdapter.notifyDataSetChanged()
                        }
                    })
        }
    }

}