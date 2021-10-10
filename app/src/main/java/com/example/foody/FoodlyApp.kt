package com.example.foody

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodlyApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}