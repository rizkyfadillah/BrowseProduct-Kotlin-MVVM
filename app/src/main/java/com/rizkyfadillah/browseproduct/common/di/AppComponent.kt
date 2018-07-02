package com.rizkyfadillah.browseproduct.common.di

import com.rizkyfadillah.browseproduct.movie.common.di.MovieComponent
import com.rizkyfadillah.browseproduct.movie.common.di.MovieModule
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