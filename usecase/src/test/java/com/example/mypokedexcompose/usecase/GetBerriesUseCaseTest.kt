package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetBerriesUseCaseTest {
    @Test
    fun `invokec calls berry repository`() = runTest {

        val berriesFlow = flowOf(sampleBerries("Aranja", "Anana"))

        val mockRepository = mock<IBerryRepository> {
            on {berries} doReturn berriesFlow
        }

        val useCase = GetBerriesUseCase(mockRepository)

        val expected = berriesFlow.toList()
        val result = useCase().toList()

        assertEquals(expected, result)
    }
}