package com.hafidmust.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hafidmust.core.domain.usecase.StoriesUseCase

class FavoriteViewModel (storiesUseCase: StoriesUseCase) : ViewModel() {
    val favoriteStories = storiesUseCase.getFavoriteStories().asLiveData()
}