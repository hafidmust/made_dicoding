package com.hafidmust.core.di

import com.hafidmust.core.data.StoriesRepository
import com.hafidmust.core.domain.repository.IStoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(storiesRepository: StoriesRepository) : IStoriesRepository
}