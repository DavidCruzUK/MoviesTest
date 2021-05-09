package uk.co.davidcruz.service.datamodel.utils

import uk.co.davidcruz.service.datamodel.DataItem
import uk.co.davidcruz.service.datamodel.MovieResponse

object FakeData {
    fun movieFakeResponse(): MovieResponse {
        val dataItem1 = DataItem("2012", "action", 1, "Movie Title", "http://www.fakeurl.com/")
        val dataItem2 = DataItem("2010", "drama", 2, "Movie Second Title", "http://www.anotherakeurl.com/")
        return MovieResponse(listOf(dataItem1, dataItem2))
    }
}