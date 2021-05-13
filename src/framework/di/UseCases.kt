package io.aethibo.framework.di

import io.aethibo.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    single<GetThoughtsUseCase> { GetThoughtsUseCaseImpl(get()) }
    single<GetThoughtUseCase> { GetThoughtUseCaseImpl(get()) }
    single<CreateThoughtUseCase> { CreateThoughtUseCaseImpl(get()) }
    single<UpdateThoughtUseCase> { UpdateThoughtUseCaseImpl(get()) }
    single<RemoveThoughtUseCase> { RemoveThoughtUseCaseImpl(get()) }
}