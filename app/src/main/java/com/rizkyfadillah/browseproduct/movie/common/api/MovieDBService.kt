package com.rizkyfadillah.browseproduct.movie.common.api

import com.rizkyfadillah.browseproduct.movie.common.model.Review
import com.rizkyfadillah.browseproduct.movie.common.model.Video
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rizky on 18/04/18.
 */

interface MovieDBService {

    @GET("3/movie/{sort}")
    fun getMovies(@Path("sort") sort: String): Observable<MovieBaseApiResponse<List<MovieEntity>>>

    @GET("3/movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: String): Observable<MovieBaseApiResponse<List<Video>>>

    @GET("3/movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: String): Observable<MovieBaseApiResponse<List<Review>>>

}