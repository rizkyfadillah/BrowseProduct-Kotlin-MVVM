package com.rizkyfadillah.browseproduct.movie.moviedetail.ui

import android.arch.lifecycle.ViewModel
import com.rizkyfadillah.browseproduct.common.model.UIModel
import com.rizkyfadillah.browseproduct.movie.common.model.Review
import com.rizkyfadillah.browseproduct.movie.common.model.Video
import com.rizkyfadillah.browseproduct.movie.common.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rizky on 26/04/18.
 */

class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovieReviews(id: String): Observable<UIModel<List<Review>>> {
        return movieRepository.getMovieReviews(id)
    }

    fun getMovieVideos(id: String): Observable<UIModel<List<Video>>> {
        return movieRepository.getMovieVideos(id)
    }

}