package com.rizkyfadillah.popularmovies.movie.movielist.ui

import com.rizkyfadillah.popularmovies.common.model.UIModel
import com.rizkyfadillah.popularmovies.movie.common.model.Movie
import com.rizkyfadillah.popularmovies.movie.common.repository.MovieRepository
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