package com.rizkyfadillah.browseproduct.movie.common.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 18/04/18.
 */

class MovieEntity(

        @SerializedName("id")
        var id: String?,

        @SerializedName("original_title")
        var originalTitle: String? = null,

        @SerializedName("poster_path")
        var posterPath: String? = null,

        @SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @SerializedName("overview")
        var overview: String? = null,

        @SerializedName("release_date")
        var releaseDate: String? = null,

        @SerializedName("vote_count")
        var voteCount: Int = 0,

        @SerializedName("vote_average")
        var voteAverage: Double = 0.toDouble()

)