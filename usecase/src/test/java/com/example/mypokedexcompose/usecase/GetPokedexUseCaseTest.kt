package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetPokedexUseCaseTest {

    @Test
    fun `invoke calls PokedexRepository`() {

        val repository = mock<IPokedexRepository>()
        val useCase = GetPokedexUseCase(repository)

        useCase()

        verify(repository).pokedex
    }

    @Test
    fun `invoke obtains a flow of lits of pokemons from PokedexRepository`() = runTest {

        val pokemonListFlow = flowOf(samplePokemonDomainList("Test1", "Test2"))
        val repository = mock<IPokedexRepository> {
            on { pokedex } doReturn pokemonListFlow
        }
        val useCase = GetPokedexUseCase(repository)
        val result = useCase().first()

        assertEquals(samplePokemonDomainList("Test1", "Test2"), result)
    }
}