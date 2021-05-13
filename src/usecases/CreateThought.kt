package io.aethibo.usecases

import io.aethibo.repositories.MainRepository

interface CreateThoughtUseCase {
    suspend operator fun invoke(title: String, content: String)
}

class CreateThoughtUseCaseImpl(private val repository: MainRepository) : CreateThoughtUseCase {

    override suspend operator fun invoke(title: String, content: String) =
        repository.createThought(title, content)
}