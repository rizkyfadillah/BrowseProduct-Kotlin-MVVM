package com.rizkyfadillah.popularmovies.movie.common.di

import com.rizkyfadillah.popularmovies.movie.moviedetail.ui.MovieDetailActivity
import com.rizkyfadillah.popularmovies.movie.movielist.ui.MovieListActivity
import dagger.Subcomponent

/**
 * Created by Rizky on 18/04/18.
 */
@MovieScope
@Subcomponent(modules = arrayOf(MovieModule::class))
interface MovieComponent {

    fun inject(movieListActivity: MovieListActivity)

    fun inject(movieDetailActivity: MovieDetailActivity)

}