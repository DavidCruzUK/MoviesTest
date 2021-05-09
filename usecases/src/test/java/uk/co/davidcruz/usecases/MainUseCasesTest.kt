package uk.co.davidcruz.usecases

import io.mockk.MockKAnnotations.init
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import uk.co.davidcruz.repository.Repository
import uk.co.davidcruz.service.datamodel.utils.FakeData

class MainUseCasesTest {
    @MockK
    lateinit var repository: Repository

    private lateinit var SUT: MainUseCases

    @Before
    fun setUp() {
        init(this, relaxUnitFun = true)

        SUT = MainUseCases(repository)
    }

    @Test
    fun `on get movies receive MoviesResponse object`() = runBlocking {
        coEvery { repository.getMovies() } returns FakeData.movieFakeResponse()

        SUT.getMovies()

        coVerify { repository.getMovies() }
    }
}