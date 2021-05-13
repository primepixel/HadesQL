package io.aethibo

import io.aethibo.framework.di.applicationModule
import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.routes.getThoughts
import io.aethibo.schema.GraphQLSchema
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val graphQl: GraphQLSchema by inject()

    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, repositoriesModule, useCasesModule)
    }

    install(Routing) {
        getThoughts(graphQl)
    }
}

