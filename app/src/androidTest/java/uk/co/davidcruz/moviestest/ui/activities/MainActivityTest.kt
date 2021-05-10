package uk.co.davidcruz.moviestest.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import uk.co.davidcruz.moviestest.R
import uk.co.davidcruz.moviestest.ui.activities.utils.FakeData

class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun on_MainActivity_launched_and_contains_data_items_error_is_NOT_displayed_and_recycler_view_is_populated() {
        // GIVEN
        activityRule.scenario.onActivity {

            // WHEN
            it.adapter.items = FakeData.movieFakeResponse().data
            it.showErrorMessageIfErrorOcurrs(it.adapter.items.isEmpty(), "No Error TEST!")

            // THEN
            assertTrue(it.adapter.itemCount > 0)
        }

        // THEN
        onView(withId(R.id.errorMessage)).check(matches(not(isDisplayed())))
    }

    @Test
    fun on_MainActivity_launched_contains_no_data_items_error_is_displayed_and_recycler_view_is_NOT_populated() {
        // GIVEN
        activityRule.scenario.onActivity {

            // WHEN
            it.adapter.items = listOf()
            it.showErrorMessageIfErrorOcurrs(it.adapter.items.isEmpty(), "Error TEST!")

            // THEN
            assertTrue(it.adapter.itemCount == 0)
        }

        // THEN
        onView(withId(R.id.errorMessage)).check(matches(isDisplayed()))
    }

    @Test
    fun on_MainActivity_launched_and_recycler_view_is_populated_click_in_row_item_and_DetailActivity_is_populated() {
        // GIVEN
        val movie = FakeData.movieFakeResponse().data[0]
        activityRule.scenario.onActivity {

            // WHEN
            it.adapter.items = FakeData.movieFakeResponse().data

            // THEN
            assertTrue(it.adapter.itemCount > 0)
        }

        // THEN
        onView(ViewMatchers.withText(movie.title)).perform(ViewActions.click())
        Thread.sleep(500)
        onView(withId(R.id.detailPosterIV)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitleTV)).check(matches(ViewMatchers.withText(movie.title)))
        onView(withId(R.id.detailYearTV)).check(matches(ViewMatchers.withText(movie.year)))

    }
}