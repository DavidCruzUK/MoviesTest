package uk.co.davidcruz.moviestest.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
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
    fun on_MainActivity_launched_searchMovieView_is_displayed() {
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
    fun on_MainActivity_launched_empty() {
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
}