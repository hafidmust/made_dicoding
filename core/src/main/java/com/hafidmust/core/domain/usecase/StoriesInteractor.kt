package com.hafidmust.core.domain.usecase

import com.hafidmust.core.data.Resource
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.core.domain.repository.IStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesInteractor @Inject constructor(private val storiesRepository: IStoriesRepository) :
    StoriesUseCase {

    override fun getAllStories(token: String): Flow<com.hafidmust.core.data.Resource<List<Stories>>> {
        return storiesRepository.getAllStories(token)
    }

    override fun getFavoriteStories(): Flow<List<Stories>> {
        return storiesRepository.getFavoriteStories()
    }

    override fun setFavoriteTourism(stories: Stories, state: Boolean) {
        return storiesRepository.setFavoriteTourism(stories, state)
    }

}