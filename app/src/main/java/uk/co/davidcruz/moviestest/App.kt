package uk.co.davidcruz.moviestest

import android.app.Application
import uk.co.davidcruz.moviestest.di.ApplicationComponent
import uk.co.davidcruz.moviestest.di.DaggerApplicationComponent

class App: Application() {
    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}