package com.rizkyfadillah.browseproduct.movie.common.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rizkyfadillah.browseproduct.movie.common.repository.MovieRepository
import com.rizkyfadillah.browseproduct.movie.moviedetail.ui.MovieDetailViewModel
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