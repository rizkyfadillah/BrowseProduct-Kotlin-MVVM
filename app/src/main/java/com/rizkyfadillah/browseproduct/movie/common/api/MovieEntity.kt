package com.rizkyfadillah.browseproduct.movie.common.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 18/04/18.
 */

class MovieEntity(

        @SerializedName("id")
        val id: String?,

        @SerializedName("original_title")
        val originalTitle: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("overview")
        val overview: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("vote_count")
        val voteCount: Int,

        @SerializedName("vote_average")
        val voteAverage: Double = 0.toDouble()

)