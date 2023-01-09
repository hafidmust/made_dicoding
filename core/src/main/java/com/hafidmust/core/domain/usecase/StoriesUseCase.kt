package com.hafidmust.core.domain.usecase

import com.hafidmust.core.data.Resource
import com.hafidmust.core.domain.model.Stories
import kotlinx.coroutines.flow.Flow

interface StoriesUseCase {
    fun getAllStories(token : String) : Flow<com.hafidmust.core.data.Resource<List<Stories>>>
    fun getFavoriteStories() : Flow<List<Stories>>
    fun setFavoriteTourism(stories : Stories, state : Boolean)
}