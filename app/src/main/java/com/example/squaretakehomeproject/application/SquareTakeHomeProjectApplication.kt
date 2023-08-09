package com.example.squaretakehomeproject.application

import android.app.Application
import com.example.squaretakehomeproject.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class SquareTakeHomeProjectApplication:Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SquareTakeHomeProjectApplication)
            modules(networkModule)
        }
    }
}