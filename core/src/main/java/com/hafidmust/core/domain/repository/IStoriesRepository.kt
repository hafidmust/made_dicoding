package com.hafidmust.core.domain.repository

import com.hafidmust.core.data.Resource
import com.hafidmust.core.domain.model.Stories
import kotlinx.coroutines.flow.Flow

interface IStoriesRepository {
    fun getAllStories(token : String) : Flow<Resource<List<Stories>>>
    fun getFavoriteStories() : Flow<List<Stories>>
    fun setFavoriteTourism(stories : Stories, state : Boolean)
}