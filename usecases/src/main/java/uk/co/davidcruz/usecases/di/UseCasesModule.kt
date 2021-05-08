package uk.co.davidcruz.usecases.di

import dagger.Module
import dagger.Provides
import uk.co.davidcruz.repository.Repository
import uk.co.davidcruz.usecases.MainUseCases

@Module
class UseCasesModule {

    @Provides
    fun provideMainUseCases(repository: Repository) = MainUseCases(repository)
}