package uk.co.davidcruz.moviestest.di.modules

import dagger.Module
import dagger.Provides
import uk.co.davidcruz.Repository
import uk.co.davidcruz.usecases.MainUseCases

@Module
class UseCasesModule {

    @Provides
    fun provideMainUseCases(repository: Repository) = MainUseCases(repository)
}