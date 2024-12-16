package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


class FetchPokedexForRegionUseCaseTest{
    @Test
    fun `invoke calls repository`() = runTest {

        val mockRepository = mock<IPokedexRepository>()

        val useCase = FetchPokedexForRegionUseCase(mockRepository)

        val range = intArrayOf(10, 20)

        useCase.invoke(range)

        verify(mockRepository, times(1)).fetchRegionalPokedex(offset = 10, limit = 10)
    }
}