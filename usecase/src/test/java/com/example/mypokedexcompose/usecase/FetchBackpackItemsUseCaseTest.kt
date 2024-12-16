package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchBackpackItemsUseCaseTest {
    @Test
    fun `invoke calls repository`() = runTest {

        val mockRepository = mock<IBackPackItemRepository>()

        val useCase = FetchBackpackItemsUseCase(mockRepository)

        useCase.invoke()

        verify(mockRepository, times(1)).fetchBackPackItems()
    }
}