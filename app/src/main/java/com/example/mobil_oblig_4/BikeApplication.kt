package com.example.mobil_oblig_4

import android.app.Application
import com.example.mobil_oblig_4.AppContainer
import com.example.mobil_oblig_4.DefaultAppContainer

class BikeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}