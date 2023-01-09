package com.hafidmust.madestories.detail

import androidx.lifecycle.ViewModel
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.core.domain.usecase.StoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailStoriesViewModel @Inject constructor(private val storiesUseCase: StoriesUseCase) :
    ViewModel() {
    fun setFavoriteStories(stories: Stories, newState: Boolean) =
        storiesUseCase.setFavoriteTourism(stories, newState)
}