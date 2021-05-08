package uk.co.davidcruz.moviestest.ui.main

import androidx.lifecycle.ViewModel
import uk.co.davidcruz.usecases.MainUseCases
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCases: MainUseCases): ViewModel() {

    suspend fun getMovies() = useCases.getMovies()
}