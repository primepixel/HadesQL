package io.aethibo

import com.apurebase.kgraphql.GraphQL
import io.aethibo.framework.di.applicationModule
import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.schema.GraphQLSchema.schemaValue
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    install(Koin) {
        SLF4JLogger()
        modules(applicationModule, repositoriesModule, useCasesModule)
    }

    install(GraphQL) {
        useDefaultPrettyPrinter = true

        endpoint = "thoughts"
        playground = true
        schema { schemaValue() }
    }
}

