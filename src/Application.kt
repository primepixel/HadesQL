package io.aethibo

import com.apurebase.kgraphql.GraphQL
import com.apurebase.kgraphql.KGraphQL
import com.apurebase.kgraphql.schema.Schema
import io.aethibo.entities.request.GraphqlRequest
import io.aethibo.framework.di.applicationModule
import io.aethibo.framework.di.repositoriesModule
import io.aethibo.framework.di.useCasesModule
import io.aethibo.schema.GraphQLSchema
import io.aethibo.schema.schemaValue
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
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

    install(GraphQL) {
        endpoint = "thoughts"
        // playground = true //// if set to true, it'll allow to open playground in the browser
        schema { schemaValue() }
    }

   /* install(Routing) {
        route("thoughts") {
            get() {
                val graphRequest = call.receive<GraphqlRequest>()
                call.respond(graphQl.schema.execute(graphRequest.query))
            }
        }
    }*/
}

