package uk.co.davidcruz.moviestest.di.modules

import dagger.Module
import dagger.Provides
import uk.co.davidcruz.moviestest.ui.main.MainViewModel
import uk.co.davidcruz.usecases.MainUseCases
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesMainViewModule(useCases: MainUseCases) = MainViewModel(useCases)

}