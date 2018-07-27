package com.rizkyfadillah.popularmovies.movie.common.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 18/04/18.
 */

data class Movie(val id: String?, val originalTitle: String?, val posterPath: String?, val backdropPath: String?,
            val overview: String?, val releaseDate: String?, val voteCount: Int?,
            val voteAverage: Double?)