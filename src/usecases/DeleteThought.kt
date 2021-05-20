package io.aethibo.usecases

import io.aethibo.repositories.MainRepository

interface DeleteThoughtUseCase {
    suspend operator fun invoke(id: String): Boolean
}

class DeleteThoughtUseCaseImpl(private val repository: MainRepository) : DeleteThoughtUseCase {

    override suspend operator fun invoke(id: String): Boolean =
        repository.deleteThought(id)
}