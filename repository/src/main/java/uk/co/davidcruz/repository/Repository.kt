package uk.co.davidcruz.repository

import uk.co.davidcruz.service.ServiceApi
import javax.inject.Inject

class Repository @Inject constructor(private val serviceApi: ServiceApi) {

    // I do Assume here the cache Room DB is not needed as Cache needs to expire in 10 minutes.
    suspend fun getMovies() = serviceApi.getMovies()

}