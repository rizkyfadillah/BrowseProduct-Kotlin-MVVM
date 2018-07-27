package com.rizkyfadillah.popularmovies.common.di

import com.rizkyfadillah.popularmovies.movie.common.di.MovieComponent
import com.rizkyfadillah.popularmovies.movie.common.di.MovieModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Rizky on 25/01/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plus(movieModule: MovieModule): MovieComponent

}