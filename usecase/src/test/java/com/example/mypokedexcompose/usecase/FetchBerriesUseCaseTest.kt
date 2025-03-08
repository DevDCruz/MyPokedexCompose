package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchBerriesUseCaseTest {

    @Test
    fun `invoke calls fetchBerries() from BerriesRepository`() = runBlocking {

        val repository = mock<IBerryRepository>()
        val useCase = FetchBerriesUseCase(repository)

        useCase()

        verify(repository, times(1)).fetchBerries()
    }
}