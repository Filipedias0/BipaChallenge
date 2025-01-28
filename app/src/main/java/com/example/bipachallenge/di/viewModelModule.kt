package com.example.bipachallenge.di

import com.example.bipachallenge.presentation.feature.nodeRankings.viewModel.NodeRankingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NodeRankingsViewModel(get()) }
}
