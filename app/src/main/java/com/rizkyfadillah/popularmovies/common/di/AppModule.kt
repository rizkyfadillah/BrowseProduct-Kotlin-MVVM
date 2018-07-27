package com.rizkyfadillah.popularmovies.common.di

import android.content.Context
import com.rizkyfadillah.popularmovies.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Rizky on 25/01/18.
 */
@Module
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

}