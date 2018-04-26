package com.rizkyfadillah.browseproduct.products.di

import com.rizkyfadillah.browseproduct.Constants
import com.rizkyfadillah.browseproduct.products.api.ProductService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Rizky on 09/04/18.
 */
@Module
class ProductsModule {

    @ProductsScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @ProductsScope
    @Provides
    fun provideProductsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ACE)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @ProductsScope
    @Provides
    fun providProductService(restAdapter: Retrofit): ProductService {
        return restAdapter.create(ProductService::class.java)
    }

}