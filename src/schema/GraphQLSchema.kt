package io.aethibo.schema

import com.apurebase.kgraphql.KGraphQL
import com.apurebase.kgraphql.schema.Schema
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import io.aethibo.entities.response.Thought
import io.aethibo.usecases.GetThoughtUseCase
import io.aethibo.usecases.GetThoughtsUseCase
import io.aethibo.usecases.GetThoughtsUseCaseImpl
import org.koin.java.KoinJavaComponent.inject

fun SchemaBuilder.schemaValue() {

    val getThoughts: GetThoughtsUseCase by inject(GetThoughtsUseCase::class.java)

    query("getThoughts") {
        description = "Get all thoughts"
        resolver { ->
            try {
                getThoughts.invoke()
            } catch (e: Exception) {
                emptyList<Thought>()
            }
        }
    }
}

object GraphQLSchema {

    val schema: Schema = KGraphQL.schema {

        val getThoughts: GetThoughtsUseCase by inject(GetThoughtsUseCase::class.java)
        val getThought: GetThoughtUseCase by inject(GetThoughtUseCase::class.java)

        /**
         * Query in Postman
         *
         * query {
         *      getThoughts {
         *          id, title, content, date
         *      }
         * }
         *
         */
        query("getThoughts") {
            description = "Get all thoughts"
            resolver { ->
                try {
                    getThoughts.invoke()
                } catch (e: Exception) {
                    emptyList<Thought>()
                }
            }
        }

        /**
         * Query in Postman
         */
        query("getThought") {
            description = "Get thought based on its ID"
            resolver { id: String ->
                try {
                    getThought.invoke(id)
                } catch (e: Exception) {

                }
            }
        }
    }
}
