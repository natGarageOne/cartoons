package com.quiz.cartoons

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppConfig: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}