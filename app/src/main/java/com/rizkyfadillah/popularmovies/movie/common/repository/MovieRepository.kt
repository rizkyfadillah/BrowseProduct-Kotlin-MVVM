package com.rizkyfadillah.popularmovies.movie.common.repository

import com.rizkyfadillah.popularmovies.common.model.UIModel
import com.rizkyfadillah.popularmovies.movie.common.api.MovieDBService
import com.rizkyfadillah.popularmovies.movie.common.api.MovieEntity
import com.rizkyfadillah.popularmovies.movie.common.api.ReviewEntity
import com.rizkyfadillah.popularmovies.movie.common.api.VideoEntity
import com.rizkyfadillah.popularmovies.movie.common.model.Movie
import com.rizkyfadillah.popularmovies.movie.common.model.Review
import com.rizkyfadillah.popularmovies.movie.common.model.Video
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

    fun getMovieVideos(id: String): Observable<UIModel<List<Video>>> {
        return movieDBService.getMovieVideos(id)
                .map {
                    val videos = mapMovieVideos().apply(it.results)
                    UIModel.success(videos, "success")
                }
                .onErrorReturn { UIModel.error(it.message) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .startWith(UIModel.loading())
    }

    private fun mapMovieVideos(): Function<List<VideoEntity>, List<Video>> {
        return Function {
            val videos = mutableListOf<Video>()
            for (videoEntity in it) {
                with(videoEntity) {
                    videos.add(Video(id, thumbnail, title))
                }
            }
            videos
        }
    }

    fun getMovieReviews(id: String): Observable<UIModel<List<Review>>> {
        return movieDBService.getMovieReviews(id)
                .map {
                    val reviews = mapMovieReviews().apply(it.results)
                    UIModel.success(reviews, "success")
                }
                .onErrorReturn { UIModel.error(it.message) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .startWith(UIModel.loading())
    }

    private fun mapMovieReviews(): Function<List<ReviewEntity>, List<Review>> {
        return Function {
            val reviews = mutableListOf<Review>()
            for (reviewEntity in it) {
                with(reviewEntity) {
                    reviews.add(Review(author, content))
                }
            }
            reviews
        }
    }

}