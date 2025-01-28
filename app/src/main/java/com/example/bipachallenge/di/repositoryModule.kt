package com.example.bipachallenge.di

import com.example.bipachallenge.data.repository.LightningRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { LightningRepository(get()) }
}
