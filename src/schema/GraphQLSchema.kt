package io.aethibo.schema

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.usecases.*
import org.koin.java.KoinJavaComponent.inject

object GraphQLSchema {

    private val getThoughts: GetThoughtsUseCase by inject(GetThoughtsUseCase::class.java)
    private val getThought: GetThoughtUseCase by inject(GetThoughtUseCase::class.java)
    private val createThought: CreateThoughtUseCase by inject(CreateThoughtUseCase::class.java)
    private val updateThought: UpdateThoughtUseCase by inject(UpdateThoughtUseCase::class.java)
    private val deleteThought: RemoveThoughtUseCase by inject(RemoveThoughtUseCase::class.java)

    fun SchemaBuilder.schemaValue() {

        /**
         * Get all thoughts
         */
        query("getThoughts") {
            description = "Get all thoughts"
            resolver { ->
                getThoughts.invoke()
            }
        }

        /**
         * Get single thought
         */
        query("getThought") {
            description = "Get thought based on its ID"
            resolver { id: String ->
                getThought.invoke(id)
            }
        }

        /**
         * Create new thought
         */
        mutation("create") {
            resolver { draft: ThoughtDraft ->
                createThought.invoke(draft)
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

        inputType<ThoughtDraft> {
            description = "The input of the thought without the identifier"
        }

        type<Thought> {
            description = "Thought object with the attributes identifier, title, content, date"
        }
    }
}
