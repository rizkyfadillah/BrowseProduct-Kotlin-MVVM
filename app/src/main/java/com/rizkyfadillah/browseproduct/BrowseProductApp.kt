package com.rizkyfadillah.browseproduct

import android.app.Application
import com.rizkyfadillah.browseproduct.common.di.DaggerAppComponent

/**
 * Created by Rizky on 25/01/18.
 */
class BrowseProductApp: Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
                .build()
    }

}