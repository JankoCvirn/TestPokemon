package com.jankocvirn.testpokemon.di

import com.jankocvirn.testpokemon.repository.ApiRepository
import org.koin.dsl.module

@JvmField
val appModule = module {
    single { ApiRepository() }
}
