package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class FetchBackPackItemByNameUseCaseTest {

    @Test
    fun `invoke calls feetchBackPackItems() from BackpackItemRepository`(): Unit = runTest {

        val repository = mock<IBackPackItemRepository>()
        val useCase = FetchBackPackItemByNameUseCase(repository)

        useCase("name")

        verify(repository, times(1)).fetchBackPackItemByName("name")
    }

    @Test
    fun `invoke obtain a flow of BackpackItemDomain from repository`() = runTest {

        val backpackItemFlow = flowOf(sampleBackPackItemDomain("name"))
        val repository = mock<IBackPackItemRepository> {
            onBlocking { fetchBackPackItemByName("name") } doReturn backpackItemFlow
        }
        val useCase = FetchBackPackItemByNameUseCase(repository)
        val result = useCase("name").first()

        assertNotNull(result)
        assertEquals(sampleBackPackItemDomain("name"), result)
    }
}