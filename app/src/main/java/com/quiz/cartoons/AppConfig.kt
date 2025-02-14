package com.quiz.cartoons

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/**
 *@Author("Natanael chavez")
 *@Email("natanaelchavez07@gmail.com")
 */
@HiltAndroidApp
class AppConfig: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}