package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetBackPackItemsUseCaseTest {
    @Test
    fun `invoke calls repository`() = runTest {

        val backPackItemFlow = flowOf(sampleBackPackItems("Pokeball", "Superball", "Ultraball"))

        val mockRepository = mock<IBackPackItemRepository> {
            on { backPackItems } doReturn backPackItemFlow
        }

        val useCase = GetBackPackItemsUseCase(mockRepository)

        val expected = backPackItemFlow.toList()
        val result = useCase().toList()

        verify(mockRepository, times(1)).backPackItems
        assertEquals(expected, result)
    }
}