package com.rizkyfadillah.browseproduct.api

import com.rizkyfadillah.browseproduct.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
* Created by Rizky on 26/12/17.
*/

interface ProductService {

    companion object Factory {
        fun getAceService(): ProductService {
            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_ACE)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ProductService::class.java)
        }
    }

    @GET("search/product/v3")
    fun getAce(
            @Query("device") device: String,
            @Query("source") source: String,
            @Query("q") q: String,
            @Query("rows") rows: Int,
            @Query("start") start: Int
    ): Observable<BaseApiResponse<AceResponse>>

}