package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest{

    @Test
    fun `invoke calls PokemonRepository`() = runTest {

        val repository = mock<IPokemonRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(samplePokemonDomain("Pikachu"))

        verify(repository, times(1)).toggleFavorite(samplePokemonDomain("Pikachu"))
    }
}