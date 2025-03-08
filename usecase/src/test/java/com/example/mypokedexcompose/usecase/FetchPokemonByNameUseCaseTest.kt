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

class FetchPokemonByNameUseCaseTest {

    @Test
    fun `invoke calls fetcPokemonByName() from PokemonRepository`(): Unit = runTest {

        val repository = mock<IPokemonRepository>()
        val useCase = FetchPokemonByNameUseCase(repository)

        useCase("test")
        verify(repository, times(1)).fetchPokemonByName("test")
    }

    @Test
    fun `invoke returns a Pokemon from repository`() = runTest {
        val pokemonFlow = flowOf(samplePokemonDomain("Pikachu"))
        val repository = mock<IPokemonRepository> {
            on { fetchPokemonByName("Pikachu") } doReturn pokemonFlow
        }
        val useCase = FetchPokemonByNameUseCase(repository)

        val result = useCase("Pikachu").first()

        assertNotNull(result)
        assertEquals(samplePokemonDomain("Pikachu"), result)
    }
}