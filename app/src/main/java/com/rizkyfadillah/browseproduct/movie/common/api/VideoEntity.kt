package com.rizkyfadillah.browseproduct.movie.common.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Rizky on 26/04/18.
 */
class VideoEntity (

        @SerializedName("id")
        val id: String,

        @SerializedName("key")
        val thumbnail: String,

        @SerializedName("name")
        val title: String

)