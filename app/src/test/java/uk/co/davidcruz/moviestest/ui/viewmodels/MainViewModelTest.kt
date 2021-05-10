package uk.co.davidcruz.moviestest.ui.viewmodels

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.co.davidcruz.moviestest.utils.FakeData
import uk.co.davidcruz.moviestest.utils.MainCoroutineRule
import uk.co.davidcruz.usecases.MainUseCases

class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var useCases: MainUseCases

    private lateinit var SUT: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        SUT = MainViewModel(useCases)
    }

    @Test
    fun `on invoke getMovies userCases getMovies is invoked`() = runBlocking {
        // GIVEN
        coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()

        // WHEN
        SUT.getMovies()

        // THEN
        coVerify { useCases.getMovies() }
    }

    @Test
    fun `on invoke getMovies is called SateFlow value will return a MovieResponse object and data is saved in listMovieDetail variable`() =
        runBlocking {
            // GIVEN
            coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()

            // WHEN
            SUT.getMovies()

            // THEN
            val movieResponse =
                (SUT.model.value as MainViewModel.UiModel.RequestMovies).movieResponse

            assertTrue(movieResponse == FakeData.movieFakeResponse())
            assertTrue(SUT.listMovieDetail == FakeData.movieFakeResponse().data)
        }

    @Test
    fun `on invoke getFilteredItems searching by correct title filter list of movies is returned is returned`() =
        runBlocking {
            // GIVEN
            coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()
            SUT.getMovies()

            // WHEN
            val filteredList = SUT.getFilteredItems("second")

            // THEN
            assertTrue(filteredList.size == 1)
            assertEquals("drama", filteredList[0].genre)
        }

    @Test
    fun `on invoke getFilteredItems searching by correct genre filter list of movies is returned is returned`() =
        runBlocking {
            // GIVEN
            coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()
            SUT.getMovies()

            // WHEN
            val filteredList = SUT.getFilteredItems("drama")

            // THEN
            assertTrue(filteredList.size == 1)
            assertEquals("Movie Second Title", filteredList[0].title)
        }

    @Test
    fun `on invoke getFilteredItems with not correct title or genre will return empty list`() =
        runBlocking {
            // GIVEN
            coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()
            SUT.getMovies()

            // WHEN
            val filteredList = SUT.getFilteredItems("rdrdrdrd")

            // THEN
            assertTrue(filteredList.isEmpty())
        }

    @Test
    fun `on invoke getMovie an specific movie will be returned`() =
        runBlocking {
            // GIVEN
            coEvery { useCases.getMovies() } returns FakeData.movieFakeResponse()
            SUT.getMovies()

            // WHEN
            SUT.getMovie(2)

            // THEN
            val movieDetail =
                (SUT.model.value as MainViewModel.UiModel.GetMovieDetail).movieDetail

            assertEquals("Movie Second Title", movieDetail.title)
        }

}