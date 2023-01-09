package com.hafidmust.madestories.di

import com.hafidmust.core.domain.usecase.StoriesInteractor
import com.hafidmust.core.domain.usecase.StoriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideStoriesUseCase(storiesInteractor: StoriesInteractor) : StoriesUseCase
}