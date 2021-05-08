package uk.co.davidcruz.usecases

import uk.co.davidcruz.repository.Repository
import javax.inject.Inject

class MainUseCases @Inject constructor(private val repository: Repository) {

    suspend fun getMovies() = repository.getMovies()

}