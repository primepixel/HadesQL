package io.aethibo.repositories

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought

interface MainRepository {
    suspend fun getAllThoughts(): List<Thought>
    suspend fun getThought(id: String): Thought?
    suspend fun createThought(draft: ThoughtDraft): Thought?
    suspend fun deleteThought(id: String): Boolean
    suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean
}