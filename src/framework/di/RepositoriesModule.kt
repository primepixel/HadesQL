package io.aethibo.framework.di

import io.aethibo.repositories.DefaultMailRepository
import io.aethibo.repositories.InMemoryRepository
import io.aethibo.repositories.MainRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single<MainRepository> { InMemoryRepository() }
    //single<MainRepository> { DefaultMailRepository() }
}