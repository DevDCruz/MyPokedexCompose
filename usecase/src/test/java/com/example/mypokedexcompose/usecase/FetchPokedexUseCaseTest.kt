package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchPokedexUseCaseTest {

    @Test
    fun `invoke calls repository`() = runTest {

        val mockRespository = mock<IPokedexRepository>()

        val useCase = FetchPokedexUseCase(mockRespository)

        useCase.invoke()

        verify(mockRespository, times(1)).fetchPokedexComplete()
    }
}