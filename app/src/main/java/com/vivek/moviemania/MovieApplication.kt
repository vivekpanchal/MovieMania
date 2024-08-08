package com.vivek.moviemania

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}