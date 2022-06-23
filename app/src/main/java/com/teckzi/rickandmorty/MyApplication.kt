package com.teckzi.rickandmorty

import android.app.Application
import com.teckzi.rickandmorty.di.Injector

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.createAppComponent(this)
    }
}