package org.betaapps.foodie

import android.app.Application
import com.google.firebase.FirebaseApp
import org.betaapps.foodie.di.initKoin

class FoodieApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        initKoin()
    }
}