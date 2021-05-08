package uk.co.davidcruz.repository.di

import dagger.Module
import dagger.Provides
import uk.co.davidcruz.repository.Repository
import uk.co.davidcruz.service.ServiceApi

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(serviceApi: ServiceApi) = Repository(serviceApi)
}