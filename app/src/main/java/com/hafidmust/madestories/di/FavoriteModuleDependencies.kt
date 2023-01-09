package com.hafidmust.madestories.di

import com.hafidmust.core.domain.usecase.StoriesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun storiesUsecase() : StoriesUseCase
}