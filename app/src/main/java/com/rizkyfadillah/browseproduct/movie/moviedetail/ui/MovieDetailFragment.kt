package com.rizkyfadillah.browseproduct.movie.moviedetail.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
 * Created by Rizky on 02/06/18.
 */

class MovieDetailFragment : Fragment() {

    private lateinit var movieReviewAdapter: MovieReviewAdapter
    private var movieReviews = ArrayList<Review>()

    @Inject lateinit var factory: MovieViewModelFactory

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    companion object {
        fun getInstance(posterPath: String?, backdropPath: String?, overview: String?,
                        originalTitle: String?, releaseDate: String?, voteAverage: Double, voteCount: Int,
                        id: String?): Fragment? {
            val movieDetailFragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putString(EXTRA_POSTER_PATH, posterPath)
            bundle.putString(EXTRA_BACKDROP_PATH, backdropPath)
            bundle.putString(EXTRA_OVERVIEW, overview)
            bundle.putString(EXTRA_ORIGINAL_TITLE, originalTitle)
            bundle.putString(EXTRA_RELEASE_DATE, releaseDate)
            bundle.putDouble(EXTRA_VOTE_AVERAGE, voteAverage)
            bundle.putInt(EXTRA_VOTE_COUNT, voteCount)
            bundle.putString(EXTRA_ID, id)
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
        }

        val EXTRA_POSTER_PATH = "poster_path"
        val EXTRA_BACKDROP_PATH = "backdrop_path"
        val EXTRA_OVERVIEW = "overview"
        val EXTRA_ORIGINAL_TITLE = "original_title"
        val EXTRA_RELEASE_DATE = "release_date"
        val EXTRA_VOTE_AVERAGE = "vote_average"
        val EXTRA_VOTE_COUNT = "vote_count"
        val EXTRA_ID = "id"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = layoutInflater.inflate(R.layout.fragment_movie_detail, container, false)

        return rootview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val app = context?.applicationContext as BrowseProductApp
        app.appComponent.plus(MovieModule()).inject(this)

        movieDetailViewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)

        val posterPath = arguments?.getString(EXTRA_POSTER_PATH)
        val backdropPath = arguments?.getString(EXTRA_BACKDROP_PATH)
        val overview = arguments?.getString(EXTRA_OVERVIEW)
        val originalTitle = arguments?.getString(EXTRA_ORIGINAL_TITLE)
        val releaseDate = arguments?.getString(EXTRA_RELEASE_DATE)
        val voteAverage = arguments?.getDouble(EXTRA_VOTE_AVERAGE, 0.0)
        val voteCount = arguments?.getInt(EXTRA_VOTE_COUNT, 0)
        val id = arguments?.getString(EXTRA_ID)

        toolbar.title = originalTitle
        text_synopsis.text = overview
        text_title.text = originalTitle
        text_rating.text = voteAverage.toString()
        text_vote_count.text = voteCount.toString()
        text_release_date.text = releaseDate

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780" + posterPath)
                .into(poster_image)

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w780" + backdropPath)
                .into(backdrop)

        movieReviews = ArrayList()
        movieReviewAdapter = MovieReviewAdapter(movieReviews)

        recyclerview_review.layoutManager = LinearLayoutManager(activity)
        recyclerview_review.adapter = movieReviewAdapter

        showReviews(id)
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