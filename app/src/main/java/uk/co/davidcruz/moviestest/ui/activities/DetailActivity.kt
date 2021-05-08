package uk.co.davidcruz.moviestest.ui.activities

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import uk.co.davidcruz.moviestest.base.BaseActivity
import uk.co.davidcruz.moviestest.databinding.ActivityMovieDetailBinding
import uk.co.davidcruz.moviestest.extensions.loadDetailUrl
import uk.co.davidcruz.moviestest.ui.viewmodels.MainViewModel
import uk.co.davidcruz.service.datamodel.DataItem

class DetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    companion object {
        const val MOVIE_ID_KEY = "DetailActivity::movie.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun createViewBinding(): ActivityMovieDetailBinding =
        ActivityMovieDetailBinding.inflate(layoutInflater)

    override fun initLifeCycleScope() {
        lifecycleScope.launchWhenStarted {
            intent?.extras?.getInt(MOVIE_ID_KEY)?.let {
                viewModel.getMovie(it)
            }
            viewModel.model.collect { onCollection(it) }
        }
    }

    private fun onCollection(model: MainViewModel.UiModel) {
        when (model) {
            is MainViewModel.UiModel.GetMovieDetail -> populateMovieDetail(model.movieDetail)
            else -> Unit
        }
    }

    private fun populateMovieDetail(item: DataItem) {
        with(binding) {
            detailPosterIV.loadDetailUrl(item.poster)
            detailTitleTV.text = item.title
            detailYearTV.text = item.year
        }
    }
}