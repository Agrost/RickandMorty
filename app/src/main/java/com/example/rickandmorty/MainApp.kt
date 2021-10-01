package com.example.rickandmorty

import android.app.Application
import android.content.Context
import com.example.rickandmorty.data.room.CharacterDatabase
import com.example.rickandmorty.di.AppComponent
import com.example.rickandmorty.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }