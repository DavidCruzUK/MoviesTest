package uk.co.davidcruz

import uk.co.davidcruz.service.ServiceApi
import javax.inject.Inject

class Repository @Inject constructor(private val serviceApi: ServiceApi) {

    // TODO: handle Error
    suspend fun getMovies() = serviceApi.getMovies()

}