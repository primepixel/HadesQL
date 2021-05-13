package io.aethibo.routes

import io.aethibo.entities.request.GraphqlRequest
import io.aethibo.schema.GraphQLSchema
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.getThoughts(graphQl: GraphQLSchema) {

    get("/thoughts") {
        val graphRequest = call.receive<GraphqlRequest>()
        call.respond(graphQl.schema.execute(graphRequest.query))
    }
}