package com.rizkyfadillah.browseproduct.movie.movielist.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.rizkyfadillah.browseproduct.MyApp
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.model.Movie
import com.rizkyfadillah.browseproduct.movie.common.di.MovieModule
import com.rizkyfadillah.browseproduct.movie.moviedetail.ui.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject

/**
 * Created by Rizky on 18/04/18.
 */

class MovieListActivity: AppCompatActivity(), MovieAdapter.OnMovieItemClickListener {

    @Inject lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movieList = ArrayList<Movie>()

        val movieAdapter = MovieAdapter(this, movieList)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = movieAdapter

        val app = application as MyApp
        app.appComponent.plus(MovieModule())
                .inject(this)

        movieListViewModel.getMovieList("popular")
                .subscribe {
                    if (it.uiState == UIModel.UIState.ERROR) {
                        progressbar.visibility = View.GONE
                        layout_error.visibility = View.VISIBLE
                        text_error_message.text = it.message
                    } else if (it.uiState == UIModel.UIState.LOADING) {
                        progressbar.visibility = View.VISIBLE
                    } else if (it.uiState == UIModel.UIState.SUCCESS) {
                        progressbar.visibility = View.GONE
                        movieList.clear()
                        movieList.addAll(it.data!!)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
    }

    override fun onClickMovie(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_ID, movie.id)
        intent.putExtra(MovieDetailActivity.EXTRA_BACKDROP_PATH, movie.backdropPath)
        intent.putExtra(MovieDetailActivity.EXTRA_POSTER_PATH, movie.posterPath)
        intent.putExtra(MovieDetailActivity.EXTRA_ORIGINAL_TITLE, movie.originalTitle)
        intent.putExtra(MovieDetailActivity.EXTRA_OVERVIEW, movie.overview)
        intent.putExtra(MovieDetailActivity.EXTRA_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MovieDetailActivity.EXTRA_VOTE_AVERAGE, movie.voteAverage)
        intent.putExtra(MovieDetailActivity.EXTRA_VOTE_COUNT, movie.voteCount)
        startActivity(intent)
    }

}