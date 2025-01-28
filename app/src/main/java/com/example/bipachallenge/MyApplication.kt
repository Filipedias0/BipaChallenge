package com.example.bipachallenge

import android.app.Application
import com.example.bipachallenge.di.repositoryModule
import com.example.bipachallenge.di.retrofitModule
import com.example.bipachallenge.di.useCaseModule
import com.example.bipachallenge.di.viewModelModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(retrofitModule, viewModelModule, useCaseModule, repositoryModule)
        }

    }
}
