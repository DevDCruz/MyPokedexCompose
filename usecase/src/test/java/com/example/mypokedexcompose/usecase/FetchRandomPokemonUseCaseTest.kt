package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FetchRandomPokemonUseCaseTest {

    @Test
    fun `invoke calls fetchRandomPokemon on repository`() = runTest {

        val mockRepository = mock<IPokemonRepository>()

        val expectedPokemon = PokemonDomain(
            name = "Pikachu",
            id = 25,
            height = 10,
            weight = 10,
            types = null,
            favorite = false,
            detailFetched = false
        )
        val expectedFlow = flowOf(expectedPokemon)
        whenever(mockRepository.fetchRandomPokemon()).thenReturn(expectedFlow)

        val useCase = FetchRandomPokemonUseCase(mockRepository)

        val resultFlow = useCase.invoke()

        verify(mockRepository, times(1)).fetchRandomPokemon()

        val emittedValues = mutableListOf<PokemonDomain?>()
        resultFlow.collect { emittedValues.add(it) }

        assertEquals(listOf(expectedPokemon), emittedValues)
    }
}
