package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


class FetchPokedexForRegionUseCaseTest {

    @Test
    fun `invoke calls fetchRegionalPokedex() from PokedexRepository`(): Unit = runTest {
        val repository = mock<IPokedexRepository>()
        val useCase = FetchPokedexForRegionUseCase(repository)

        useCase(intArrayOf(0, 10))

        verify(repository, times(1)).fetchRegionalPokedex(0, 10)
    }
}