package uk.co.davidcruz.moviestest.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import uk.co.davidcruz.moviestest.base.BaseActivity
import uk.co.davidcruz.moviestest.databinding.ActivityMainBinding
import uk.co.davidcruz.moviestest.ui.adapter.MoviesAdapter
import uk.co.davidcruz.service.datamodel.MovieResponse
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        CoroutineScope(Dispatchers.Main).launch {
            showProgressBar(false)
        }
    }

    private val adapter = MoviesAdapter { id ->
        // TODO: go to Detail View Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setupRecyclerView()
    }

    override fun createViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initLifeCycleScope() {
        showProgressBar(true)
        lifecycleScope.launchWhenStarted {
            CoroutineScope(Dispatchers.IO).launch(errorHandler) {
                viewModel.getMovies()
            }
            viewModel.model.collect { onCollect(it) }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changeSpanCountOnOrientationChange(newConfig)
    }

    private fun changeSpanCountOnOrientationChange(newConfig: Configuration) {
        val spanCount = if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3
        binding.gridRV.layoutManager = GridLayoutManager(this, spanCount)
    }

    private suspend fun onCollect(uiModel: MainViewModel.UiModel) {
        when (uiModel) {
            is MainViewModel.UiModel.RequestMovies -> onSuccessResponse(uiModel.movieResponse)
        }
    }

    private suspend fun onSuccessResponse(movies: MovieResponse?) {
        withContext(Dispatchers.Main) {
            showProgressBar(false)
            movies?.data?.let { adapter.items = it }
        }
    }

    private fun setupRecyclerView() {
        binding.gridRV.setHasFixedSize(true)
        binding.gridRV.adapter = adapter
    }

    private fun showProgressBar(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}