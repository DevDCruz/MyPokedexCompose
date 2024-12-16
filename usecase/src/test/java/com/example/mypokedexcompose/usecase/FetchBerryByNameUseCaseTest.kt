package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class FetchBerryByNameUseCaseTest {
    @Test
    fun `invoke calls repository`() = runTest {

        val berry = sampleBerry("Aranja")

        val berryFlow = flowOf(berry)

        val mockRepository = mock<IBerryRepository>()

        whenever(mockRepository.fetchBerryByName("Aranja")).thenReturn(berryFlow)

        val useCase = FetchBerryByNameUseCase(mockRepository)

        val result = useCase.invoke("Aranja").first()

        assertEquals(berry, result)

        verify(mockRepository, times(1)).fetchBerryByName("Aranja")
    }
}