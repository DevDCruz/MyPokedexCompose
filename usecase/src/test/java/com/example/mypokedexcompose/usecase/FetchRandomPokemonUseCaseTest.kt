package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


class FetchRandomPokemonUseCaseTest {

    @Test
    fun `invoke calls fetchRamdonPokemon from PokemonRepository`(): Unit = runTest {

        val repository = mock<IPokemonRepository>()
        val useCase = FetchRandomPokemonUseCase(repository)

        useCase()

        verify(repository, times(1)).fetchRandomPokemon()
    }

    @Test
    fun `invoke obtains a flow of pokemonDomain`() = runTest {

        val pokemonFlow = flowOf(samplePokemonDomain("Pikachu"))
        val repository = mock<IPokemonRepository> {
            onBlocking { fetchRandomPokemon() } doReturn pokemonFlow
        }
        val useCase = FetchRandomPokemonUseCase(repository)

        val result = useCase().first()

        assertNotNull(result)
        assertEquals(samplePokemonDomain("Pikachu"), result)
    }
}