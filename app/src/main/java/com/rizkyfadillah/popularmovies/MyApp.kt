package com.rizkyfadillah.popularmovies

import android.app.Application
import com.rizkyfadillah.popularmovies.common.di.DaggerAppComponent

/**
 * Created by Rizky on 25/01/18.
 */
class MyApp: Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
                .build()
    }

}