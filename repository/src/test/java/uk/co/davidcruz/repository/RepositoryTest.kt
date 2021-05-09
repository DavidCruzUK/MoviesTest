package uk.co.davidcruz.repository

import io.mockk.MockKAnnotations
import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import uk.co.davidcruz.repository.utils.FakeData
import uk.co.davidcruz.service.ServiceApi

class RepositoryTest {

    @MockK
    lateinit var serviceApi: ServiceApi

    private lateinit var SUT: Repository

    @Before
    fun setUp() {
        init(this, relaxUnitFun = true)

        SUT = Repository(serviceApi)
    }

    @Test
    fun `on get movies receive MoviesResponse object`() = runBlocking {
        coEvery { serviceApi.getMovies() } returns FakeData.movieFakeResponse()

        SUT.getMovies()

        coVerify { serviceApi.getMovies() }
    }
}