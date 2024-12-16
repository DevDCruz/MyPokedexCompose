package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class ToggleFavoriteUseCaseTest {
    @Test
    fun `invoque calls repository`() = runTest {
        val pokemon = samplePokemonName("Pikachu")
        val mockRepository = mock<IPokemonRepository>()
        val useCase = ToggleFavoriteUseCase(mockRepository)
        useCase.invoke(pokemon)
        verify(mockRepository, times(1)).toggleFavorite(pokemon)
    }
}