package uk.co.davidcruz.moviestest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.co.davidcruz.service.datamodel.MovieResponse
import uk.co.davidcruz.usecases.MainUseCases
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCases: MainUseCases) : ViewModel() {

    sealed class UiModel {
        data class RequestMovies(val movieResponse: MovieResponse?) : UiModel()
    }

    private val _model: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.RequestMovies(null))
    val model: StateFlow<UiModel> get() = _model

    suspend fun getMovies() {
        val movies = useCases.getMovies()
        viewModelScope.launch {
            _model.value = UiModel.RequestMovies(movies)
        }
    }

}