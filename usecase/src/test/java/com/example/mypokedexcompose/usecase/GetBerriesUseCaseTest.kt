package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetBerriesUseCaseTest {

    @Test
    fun `invoke calls BerryRepository`() {

        val repository = mock<IBerryRepository>()
        val useCase = GetBerriesUseCase(repository)

        useCase()

        verify(repository).berries
    }

    @Test
    fun `invoke obtain a flow of list of Berries from BerryRepository`() = runTest {

        val berryListFlow = flowOf(sampleBerryDomainList("Test1", "Test2"))
        val repository = mock<IBerryRepository> {
            on { berries } doReturn berryListFlow
        }

        val useCase = GetBerriesUseCase(repository)
        val result = useCase().first()

        assertEquals(sampleBerryDomainList("Test1", "Test2"), result)
    }
}