package io.aethibo.usecases

import io.aethibo.entities.request.ThoughtDraft
import io.aethibo.entities.response.Thought
import io.aethibo.repositories.MainRepository

interface CreateThoughtUseCase {
    suspend operator fun invoke(draft: ThoughtDraft): Thought?
}

class CreateThoughtUseCaseImpl(private val repository: MainRepository) : CreateThoughtUseCase {

    override suspend operator fun invoke(draft: ThoughtDraft) =
        repository.createThought(draft)
}