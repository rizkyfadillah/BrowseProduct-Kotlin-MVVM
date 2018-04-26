package com.rizkyfadillah.browseproduct.movie.common.repository

import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.api.MovieDBService
import com.rizkyfadillah.browseproduct.movie.common.api.MovieEntity
import com.rizkyfadillah.browseproduct.movie.common.model.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Rizky on 18/04/18.
 */

class MovieRepository @Inject constructor(private val movieDBService: MovieDBService) {

    fun getMovieList(sort: String): Observable<UIModel<List<Movie>>> {
        return movieDBService.getMovies(sort)
                .map {
                    val movies = mapMovies().apply(it.results)
                    UIModel.success(movies, "success")
                }
                .onErrorReturn { UIModel.error(it.message) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .startWith(UIModel.loading())
    }

    fun mapMovies(): Function<List<MovieEntity>, List<Movie>> {
        return Function {
            val movies = mutableListOf<Movie>()
            for (movieEntity in it) {
                with(movieEntity) {
                    movies.add(Movie(id, originalTitle, posterPath, backdropPath, overview, releaseDate, voteCount, voteAverage))
                }
            }
            movies
        }
    }

}