package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchPokedexUseCaseTest {

    @Test
    fun `invoke calls fetchPokedexcomplete() from PokedexRepository`() = runBlocking {

        val repository = mock<IPokedexRepository>()
        val useCase = FetchPokedexUseCase(repository)

        useCase()

        verify(repository, times(1)).fetchPokedexComplete()
    }
}