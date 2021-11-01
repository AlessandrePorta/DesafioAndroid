package com.example.desafioandroid2.application

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioandroid2.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(appModule, netWorkModule))
        }
    }
}