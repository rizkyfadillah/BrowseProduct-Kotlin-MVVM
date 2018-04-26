package com.rizkyfadillah.browseproduct.movie.movielist.ui

import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.model.Movie
import com.rizkyfadillah.browseproduct.movie.common.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rizky on 18/04/18.
 */

class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) {

    fun getMovieList(sort: String): Observable<UIModel<List<Movie>>> {
        return movieRepository.getMovieList(sort)
    }

}