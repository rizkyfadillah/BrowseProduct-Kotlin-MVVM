package com.rizkyfadillah.browseproduct.movie.moviedetail.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rizkyfadillah.browseproduct.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MovieDetailFragment
                        .getInstance(
                                intent.getStringExtra(MovieDetailFragment.EXTRA_POSTER_PATH),
                                intent.getStringExtra(MovieDetailFragment.EXTRA_BACKDROP_PATH),
                                intent.getStringExtra(MovieDetailFragment.EXTRA_OVERVIEW),
                                intent.getStringExtra(MovieDetailFragment.EXTRA_ORIGINAL_TITLE),
                                intent.getStringExtra(MovieDetailFragment.EXTRA_RELEASE_DATE),
                                intent.getDoubleExtra(MovieDetailFragment.EXTRA_VOTE_AVERAGE, 0.0),
                                intent.getIntExtra(MovieDetailFragment.EXTRA_VOTE_COUNT, 0),
                                intent.getStringExtra(MovieDetailFragment.EXTRA_ID)
                        ))
                .commit()
    }

}