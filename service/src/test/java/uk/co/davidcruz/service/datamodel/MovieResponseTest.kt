package uk.co.davidcruz.service.datamodel

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import uk.co.davidcruz.service.datamodel.utils.FakeData

class MovieResponseTest {

    private lateinit var SUT: MovieResponse

    @Before
    fun setUp() {
        SUT = FakeData.movieFakeResponse()
    }

    @Test
    fun `verify MovieResponse data is type list of DataItem`() {
        // GIVEN
        val errorMessage = "Data expected is List<DataItem>"

        // THEN
        assertTrue(errorMessage, SUT.data is List<DataItem>)
    }

    @Test
    fun `verify MovieResponse first title is Movie Title`() {
        // GIVEN
        val errorMessage = "Title return different data or title"

        // THEN
        assertEquals(errorMessage, "Movie Title", SUT.data[0].title)
    }

    @Test
    fun `verify MovieResponse first id is 1`() {
        // GIVEN
        val errorMessage = "ID returned is different"

        // THEN
        assertEquals(errorMessage, 1, SUT.data[0].id)
    }

    @Test
    fun `verify MovieResponse first genre is action`() {
        // GIVEN
        val errorMessage = "ID returned is different"

        // THEN
        assertEquals(errorMessage, "action", SUT.data[0].genre)
    }

    @Test
    fun `verify MovieResponse first poster is fakeurl`() {
        // GIVEN
        val errorMessage = "url is different"

        // THEN
        assertEquals(errorMessage, "http://www.fakeurl.com/", SUT.data[0].poster)
    }

    @Test
    fun `verify MovieResponse first year is 2012`() {
        // GIVEN
        val errorMessage = "url is different"

        // THEN
        assertEquals(errorMessage, "2012", SUT.data[0].year)
    }
}