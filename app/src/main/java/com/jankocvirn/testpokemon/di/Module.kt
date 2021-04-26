package com.jankocvirn.testpokemon.di

import com.jankocvirn.testpokemon.repository.ApiRepository
import com.jankocvirn.testpokemon.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val appModule = module {

    single { ApiRepository() }

    viewModel { MainViewModel(get()) }
}
