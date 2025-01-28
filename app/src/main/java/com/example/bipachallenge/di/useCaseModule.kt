package com.example.bipachallenge.di

import com.example.bipachallenge.domain.useCase.GetNodeRankingsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNodeRankingsUseCase(get()) }
}
