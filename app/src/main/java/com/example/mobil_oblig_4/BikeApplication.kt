package com.example.mobil_oblig_4

import android.app.Application
import com.example.mobil_oblig_4.AppContainer
import com.example.mobil_oblig_4.DefaultAppContainer
//klassen opprettes når appen starter.
class BikeApplication : Application() {

    //lagrer AppContainer.
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        //Når appen starter, opprettes containeren en gang og brukes videre i hele appen.
    }
}