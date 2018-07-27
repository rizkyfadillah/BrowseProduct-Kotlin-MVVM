package com.rizkyfadillah.popularmovies.movie.common.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 18/04/18.
 */

class MovieBaseApiResponse<T>(@SerializedName("results") val results: T)