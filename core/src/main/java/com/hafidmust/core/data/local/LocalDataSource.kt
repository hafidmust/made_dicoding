package com.hafidmust.core.data.local

import com.hafidmust.core.data.local.entity.StoryEntity
import com.hafidmust.core.data.local.room.StoriesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val storiesDao: StoriesDao) {
    fun getAllStories() : Flow<List<StoryEntity>> = storiesDao.getAllStories()
    fun getFavoriteStories() : Flow<List<StoryEntity>> = storiesDao.getFavoriteStories()
    suspend fun insertStories(storiesList : List<StoryEntity>) = storiesDao.insertStories(storiesList)
    fun setFavoriteStories(stories : StoryEntity, newState : Boolean) {
        stories.isFavorite = newState
        storiesDao.updateFavoriteStories(stories)
    }
}