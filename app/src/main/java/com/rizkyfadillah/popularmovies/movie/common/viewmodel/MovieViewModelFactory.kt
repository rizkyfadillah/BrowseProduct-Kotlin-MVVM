package com.rizkyfadillah.popularmovies.movie.common.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rizkyfadillah.popularmovies.movie.common.repository.MovieRepository
import com.rizkyfadillah.popularmovies.movie.moviedetail.ui.MovieDetailViewModel
import javax.inject.Inject

/**
 * Created by Rizky on 04/06/18.
 */

class MovieViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MovieDetailViewModel(movieRepository) as T
    }

}