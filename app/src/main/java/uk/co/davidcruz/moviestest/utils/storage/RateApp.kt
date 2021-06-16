package uk.co.davidcruz.moviestest.utils.storage

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE

class RateApp(application: Application) {

    val sharePref = application.getSharedPreferences(SHARE_PREF_KEY, MODE_PRIVATE)

    fun saveUserRateApp(activity: Activity) {
        val editor = sharePref.edit()
        editor.putBoolean(RATE_APP_KEY, true)
        editor.apply()
    }

    fun getUserHasRatedTheApp() = sharePref.getBoolean(RATE_APP_KEY, false)

    companion object {
        const val SHARE_PREF_KEY = "RateApp::SHARE_PREF_KEY"
        const val RATE_APP_KEY = "RateApp::RATE_APP_KEY"
    }
}