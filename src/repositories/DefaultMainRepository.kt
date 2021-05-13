package io.aethibo.repositories

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.entities.tables.Thoughts
import io.aethibo.framework.utils.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import java.util.*

class DefaultMainRepository : MainRepository {

    override suspend fun getAllThoughts(): List<Thought> = dbQuery {
        Thoughts.selectAll()
            .map { Thoughts.toThought(it) }
    }

    override suspend fun getThought(id: String): Thought? = dbQuery {
        Thoughts.select { Thoughts.id eq id }
            .mapNotNull { Thoughts.toThought(it) }
            .singleOrNull()
    }

    override suspend fun createThought(draft: ThoughtDraft): Thought? = dbQuery {
        val insertThought = Thoughts.insert {
            it[id] = UUID.randomUUID().toString()
            it[title] = draft.title
            it[content] = draft.content
            it[date] = System.currentTimeMillis()
        }

        val result = insertThought.resultedValues?.get(0)

        if (result != null) Thoughts.toThought(result)
        else null
    }

    override suspend fun removeThought(id: String): Boolean = dbQuery {
        val deletedRows: Int = Thoughts.deleteWhere { Thoughts.id eq id }

        deletedRows > 0
    }

    override suspend fun updateThought(id: String, draft: ThoughtDraft): Boolean = dbQuery {
        val updatedRows: Int = Thoughts.update({ Thoughts.id eq id }) {
            it[title] = draft.title
            it[content] = draft.content
        }

        updatedRows > 0
    }
}