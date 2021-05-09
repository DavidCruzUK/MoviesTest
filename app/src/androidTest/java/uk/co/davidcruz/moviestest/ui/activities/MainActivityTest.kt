package uk.co.davidcruz.moviestest.ui.activities

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import uk.co.davidcruz.moviestest.R

class MainActivityTest {

    @Test
    fun on_MainActivity_launched_searchMovieView_is_displayed() {
        // GIVEN
        ActivityScenario.launch(MainActivity::class.java)

        // THEN
        onView(withId(R.id.searchMovieView)).check(matches(isDisplayed()))
    }

}