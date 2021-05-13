package io.aethibo.schema

import com.apurebase.kgraphql.KGraphQL
import com.apurebase.kgraphql.schema.Schema
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.usecases.*
import org.koin.java.KoinJavaComponent.inject

object GraphQLSchema {

    private val getThoughts: GetThoughtsUseCase by inject(GetThoughtsUseCase::class.java)
    private val getThought: GetThoughtUseCase by inject(GetThoughtUseCase::class.java)
    private val createThought: CreateThoughtUseCase by inject(CreateThoughtUseCase::class.java)
    private val updateThought: UpdateThoughtUseCase by inject(UpdateThoughtUseCase::class.java)
    private val deleteThought: RemoveThoughtUseCase by inject(RemoveThoughtUseCase::class.java)

    val schema: Schema = KGraphQL.schema {

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
                getThoughts.invoke()
            }
        }

        /**
         * Query in Postman
         */
        query("getThought") {
            description = "Get thought based on its ID"
            resolver { id: String ->
                getThought.invoke(id)
            }
        }

        /**
         * Create thought
         */
        mutation("create") {
            description = "Creates new thought"
            resolver { title: String, content: String ->
                try {
                    createThought.invoke(title, content)
                    true
                } catch (ex: Exception) {
                    false
                }
            }
        }

        /**
         * Update thought
         */
        mutation("updateThought") {
            description = "Update existing thought"
            resolver { id: String, title: String, content: String ->
                updateThought.invoke(id, ThoughtDraft(title, content))
            }
        }

        /**
         * Remove thought
         */
        mutation("removeThought") {
            description = "Remove thought"
            resolver { id: String ->
                deleteThought.invoke(id)
            }
        }
    }
}
