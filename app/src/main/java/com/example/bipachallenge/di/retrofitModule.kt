package com.example.bipachallenge.di

import com.example.bipachallenge.data.api.LightningApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { provideRetrofit() }
    single { provideApiService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl("https://mempool.space/")
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}

fun provideApiService(retrofit: Retrofit): LightningApiService {
    return retrofit.create(LightningApiService::class.java)
}
