package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FetchBackPackItemByNameUseCaseTest {
    @Test
    fun `invoke calls repository`(): Unit = runBlocking {
        val backPackItemFlow = flowOf(sampleBackPackItem("Pokeball"))
        val mockRepository = mock<IBackPackItemRepository> ()
        whenever(mockRepository.fetchBackPackItemByName("Pokeball")).doReturn(backPackItemFlow)
        val useCase = FetchBackPackItemByNameUseCase(mockRepository)
        val result = useCase("Pokeball").first()
        val expected = sampleBackPackItem("Pokeball")
        assert(result == expected)
        verify(mockRepository).fetchBackPackItemByName("Pokeball")
    }
}