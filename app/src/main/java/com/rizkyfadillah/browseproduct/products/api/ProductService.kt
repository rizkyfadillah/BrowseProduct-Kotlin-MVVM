package com.rizkyfadillah.browseproduct.products.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
* Created by Rizky on 26/12/17.
*/

interface ProductService {

    @GET("search/product/v3")
    fun getAce(
            @Query("device") device: String,
            @Query("source") source: String,
            @Query("q") q: String,
            @Query("rows") rows: Int,
            @Query("start") start: Int
    ): Observable<ProductBaseApiResponse<AceResponse>>

}