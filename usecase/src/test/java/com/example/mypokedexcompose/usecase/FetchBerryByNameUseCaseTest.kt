package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
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

class FetchBerryByNameUseCaseTest {

    @Test
    fun `invoke calls fetchBerryByName() from BerriesRepository`(): Unit = runTest {

        val repository = mock<IBerryRepository>()
        val useCase = FetchBerryByNameUseCase(repository)

        useCase("name")

        verify(repository, times(1)).fetchBerryByName("name")
    }

    @Test
    fun `invoke obtain a BerryDomain from BerryRepository`() = runTest {

        val berryFlow = flowOf(sampleBerryDomain("name"))
        val repository = mock<IBerryRepository> {
            onBlocking { fetchBerryByName("name") } doReturn berryFlow
        }

        val useCase = FetchBerryByNameUseCase(repository)
        val result = useCase("name").first()

        assertNotNull(result)
        assertEquals(sampleBerryDomain("name"), result)
    }
}