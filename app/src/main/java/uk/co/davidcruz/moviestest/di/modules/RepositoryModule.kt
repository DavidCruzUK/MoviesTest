package uk.co.davidcruz.moviestest.di.modules

import dagger.Module
import dagger.Provides
import uk.co.davidcruz.Repository
import uk.co.davidcruz.service.ServiceApi

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(serviceApi: ServiceApi) = Repository(serviceApi)
}