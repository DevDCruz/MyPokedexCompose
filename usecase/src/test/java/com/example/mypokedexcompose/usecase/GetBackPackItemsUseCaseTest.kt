package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class GetBackPackItemsUseCaseTest {
    @Test
    fun `invoke  calls BackPackItemRepository`() {
        val repository = mock<IBackPackItemRepository>()
        val useCase = GetBackPackItemsUseCase(repository)

        useCase()

        verify(repository).backPackItems
    }

    @Test
    fun `invoke obtains a flow of lits of BackPackItemDomain`() = runTest {

        val backPackItemListFlow = flowOf(sampleBackPackItemDomainList("Test1", "Test2"))
        val repository = mock<IBackPackItemRepository> {
            on { backPackItems } doReturn backPackItemListFlow
        }

        val useCase = GetBackPackItemsUseCase(repository)
        val result = useCase().first()

        assertEquals(sampleBackPackItemDomainList("Test1", "Test2"), result)
    }
}