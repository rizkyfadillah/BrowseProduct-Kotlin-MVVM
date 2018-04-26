package com.rizkyfadillah.browseproduct.movie.movielist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.rizkyfadillah.browseproduct.BrowseProductApp
import com.rizkyfadillah.browseproduct.R
import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.model.Movie
import com.rizkyfadillah.browseproduct.movie.common.di.MovieModule
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.layout_error.*
import javax.inject.Inject

/**
 * Created by Rizky on 18/04/18.
 */

class MovieListActivity: AppCompatActivity() {

    @Inject
    lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movieList = ArrayList<Movie>()

        val movieAdapter = MovieAdapter(movieList)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = movieAdapter

        val app = application as BrowseProductApp
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

}