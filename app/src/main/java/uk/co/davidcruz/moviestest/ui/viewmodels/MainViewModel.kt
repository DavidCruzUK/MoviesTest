package uk.co.davidcruz.moviestest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.co.davidcruz.service.datamodel.DataItem
import uk.co.davidcruz.service.datamodel.MovieResponse
import uk.co.davidcruz.usecases.MainUseCases
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCases: MainUseCases) : ViewModel() {

    sealed class UiModel {
        data class RequestMovies(val movieResponse: MovieResponse?) : UiModel()
        data class GetMovieDetail(val movieDetail: DataItem) : UiModel()
    }

    private val _model: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.RequestMovies(null))
    val model: StateFlow<UiModel> get() = _model

    private lateinit var listMovieDetail: List<DataItem>

    suspend fun getMovies() {
        val movies = useCases.getMovies()
        viewModelScope.launch {
            listMovieDetail = movies.data
            _model.value = UiModel.RequestMovies(movies)
        }
    }

    fun getMovie(id: Int) {
        listMovieDetail.find { it.id == id }?.let { movie ->
            _model.value = UiModel.GetMovieDetail(movie)
        }
    }

}