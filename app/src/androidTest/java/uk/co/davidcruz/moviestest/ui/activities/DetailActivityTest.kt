package uk.co.davidcruz.moviestest.ui.activities

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import uk.co.davidcruz.moviestest.R
import uk.co.davidcruz.service.datamodel.DataItem

class DetailActivityTest {

    private fun getIntent() =
        Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.MOVIE_ID_KEY, 912312)
        }

    @get:Rule
    var activityRule: ActivityScenarioRule<DetailActivity> =
        ActivityScenarioRule<DetailActivity>(getIntent())

    @Test
    fun on_DetailActivity_launched_movie_is_find_by_id_and_displayed() {
        // GIVEN
        val movieId = 912312
        val title = "Dunkirk"
        val year = "2017"

        // WHEN
        activityRule.scenario.onActivity {
            it.viewModel._listMovieDetail = FakeData.movieFakeResponse().data
            it.viewModel.getMovie(movieId)
        }

        // THEN
        Espresso.onView(ViewMatchers.withId(R.id.detailPosterIV))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.detailTitleTV))
            .check(matches(withText(title)))

        Espresso.onView(ViewMatchers.withId(R.id.detailYearTV))
            .check(matches(withText(year)))
    }
}