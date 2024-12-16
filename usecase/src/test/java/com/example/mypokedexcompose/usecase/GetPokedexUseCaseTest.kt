package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


class GetPokedexUseCaseTest {
    @Test
    fun `invoke calls repository`() = runTest {

        val pokedexFlow = flowOf(samplePokemons("Pikachu", "Charmander"))

        val mockRepository = mock<IPokedexRepository> {
            on { pokedex } doReturn pokedexFlow
        }

        val useCase = GetPokedexUseCase(mockRepository)

        val expected = pokedexFlow.toList()
        val result = useCase().toList()

        verify(mockRepository, times(1)).pokedex
        assertEquals(expected, result)
    }
}