package com.example.mypokedexcompose.usecase

import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FetchPokemonByNameUseCaseTest{

    @Test
    fun `invoke calls repository`() {
        val pokemonFlow = flowOf(samplePokemonName("Pikachu"))
        val useCase = FetchPokemonByNameUseCase(mock {
            on { fetchPokemonByName("Pikachu") } doReturn pokemonFlow
        })
        val result = useCase("Pikachu")

        assertEquals(pokemonFlow, result)
    }
}