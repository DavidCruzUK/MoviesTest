package uk.co.davidcruz.service

import retrofit2.http.GET
import uk.co.davidcruz.service.datamodel.MovieResponse

interface ServiceApi {

    @GET("movies")
    suspend fun getMovies(): MovieResponse

}
