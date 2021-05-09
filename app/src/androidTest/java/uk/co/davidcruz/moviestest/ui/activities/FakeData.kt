package uk.co.davidcruz.moviestest.ui.activities

import uk.co.davidcruz.service.datamodel.DataItem
import uk.co.davidcruz.service.datamodel.MovieResponse

object FakeData {

    private val mockListDataItem =
        DataItem(
            "2017",
            "History",
            912312,
            "Dunkirk",
            "https://image.tmdb.org/t/p/w370_and_h556_bestv2/ebSnODDg9lbsMIaWg2uAbjn7TO5.jpg"
        )


    private val mockListDataItem2 =
        DataItem(
            "2017",
            "Action",
            11241,
            "Jumanji: welcome to the jungle",
            "https://image.tmdb.org/t/p/w370_and_h556_bestv2/bXrZ5iHBEjH7WMidbUDQ0U2xbmr.jpg"
        )

    fun movieFakeResponse(): MovieResponse {
        return MovieResponse(listOf(mockListDataItem, mockListDataItem2))
    }
}