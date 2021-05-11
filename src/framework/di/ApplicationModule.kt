package io.aethibo.framework.di

import io.aethibo.framework.utils.DatabaseFactory
import io.aethibo.schema.GraphQLSchema
import org.koin.dsl.module

val applicationModule = module {
    single { DatabaseFactory }
    single { GraphQLSchema }
}