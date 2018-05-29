package com.rizkyfadillah.browseproduct.movie.common.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 30/04/18.
 */
class ReviewEntity (

        @SerializedName("author")
        val author: String,

        @SerializedName("content")
        val content: String

)