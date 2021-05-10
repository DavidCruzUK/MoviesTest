package uk.co.davidcruz.moviestest.ui.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.annotation.VisibleForTesting
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import uk.co.davidcruz.moviestest.R
import uk.co.davidcruz.moviestest.base.BaseActivity
import uk.co.davidcruz.moviestest.databinding.ActivityMainBinding
import uk.co.davidcruz.moviestest.ui.adapter.MoviesAdapter
import uk.co.davidcruz.moviestest.ui.viewmodels.MainViewModel
import uk.co.davidcruz.service.datamodel.DataItem
import uk.co.davidcruz.service.datamodel.MovieResponse

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var uiStateJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar(false)
            showRequestMoviesFA(true)
            showErrorMessageIfErrorOcurrs(errorMessage = getString(R.string.error_no_internet_or_cache))
        }
    }

    @VisibleForTesting
    val adapter = MoviesAdapter { id ->
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtras(bundleOf(DetailActivity.MOVIE_ID_KEY to id))
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setupClickListeners()
        setupRecyclerView()
        searchViewQueryListener()
    }

    private fun setupClickListeners() {
        binding.requestMoviesFA.setOnClickListener {
            requestMovies()
        }
    }

    override fun createViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initLifeCycleScope() {
        uiStateJob = lifecycleScope.launchWhenStarted {
            requestMovies()
            viewModel.model.collect { onCollect(it) }
        }
    }

    override fun onStop() {
        uiStateJob?.cancel()
        super.onStop()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changeSpanCountOnOrientationChange(newConfig)
    }

    private fun requestMovies() {
        CoroutineScope(Dispatchers.IO).launch(errorHandler) {
            viewModel.getMovies()
        }
    }

    private fun changeSpanCountOnOrientationChange(newConfig: Configuration) {
        setCorrectSpanCount(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
    }

    private fun setCorrectSpanCount(isLandscape: Boolean) {
        binding.gridRV.layoutManager = GridLayoutManager(this, if (isLandscape) 5 else 3)
    }

    private fun setupRecyclerView() {
        setCorrectSpanCount(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        binding.gridRV.setHasFixedSize(true)
        binding.gridRV.adapter = adapter
    }

    private suspend fun onCollect(uiModel: MainViewModel.UiModel) {
        when (uiModel) {
            is MainViewModel.UiModel.RequestMovies -> onSuccessResponse(uiModel.movieResponse)
            else -> Unit
        }
    }

    private fun onNewFilteredListIsReceived(newFilteredList: List<DataItem>) {
        showErrorMessageIfErrorOcurrs(
            newFilteredList.isEmpty(),
            getString(R.string.error_no_data_with_that_filter)
        )
        adapter.items = newFilteredList
    }

    private suspend fun onSuccessResponse(movies: MovieResponse?) {
        showProgressBar(false)
        showRequestMoviesFA(false)
        showErrorMessageIfErrorOcurrs(false)
        withContext(Dispatchers.Main) {
            movies?.data?.let { adapter.items = it }
        }
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showRequestMoviesFA(show: Boolean) {
        binding.requestMoviesFA.visibility = if (show) View.VISIBLE else View.GONE
    }

    @VisibleForTesting
    fun showErrorMessageIfErrorOcurrs(showError: Boolean = true, errorMessage: String? = "") {
        with(binding.errorMessage) {
            visibility = if (showError) View.VISIBLE else View.GONE
            text = if (showError) errorMessage else ""
        }
    }

    private fun onSearchEventListener(text: String?) {
        showProgressBar(false)
        text?.let {
            onNewFilteredListIsReceived(viewModel.getFilteredItems(it))
        }
    }

    private fun searchViewQueryListener() {
        binding.searchMovieView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                onSearchEventListener(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                onSearchEventListener(newText)
                return true
            }
        })
    }

}