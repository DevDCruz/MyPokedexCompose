package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchBackpackItemsUseCaseTest {

    @Test
    fun `invoke calls fetchBackPackItems on repository`() = runBlocking {
        val repository = mock<IBackPackItemRepository>()
        val useCase = FetchBackpackItemsUseCase(repository)

        useCase()

        verify(repository, times(1)).fetchBackPackItems()
    }
}