package io.aethibo.repositories

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought

class DefaultMainRepository : MainRepository {

    override suspend fun getAllThoughts(): List<Thought> {
        TODO("Not yet implemented")
    }

    override suspend fun getThought(id: String): Thought? {
        TODO("Not yet implemented")
    }

    override suspend fun createThought(title: String, content: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeThought(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean {
        TODO("Not yet implemented")
    }
}