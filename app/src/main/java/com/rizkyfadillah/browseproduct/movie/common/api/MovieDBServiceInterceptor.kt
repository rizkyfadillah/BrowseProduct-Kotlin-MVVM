package com.rizkyfadillah.browseproduct.movie.common.api

import com.rizkyfadillah.browseproduct.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Rizky on 20/04/18.
 */
class MovieDBServiceInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_TOKEN)
                .build()

        request = request.newBuilder()
                .url(url)
                .build()

        return chain.proceed(request)
    }

}