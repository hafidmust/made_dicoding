package com.hafidmust.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hafidmust.core.data.local.entity.StoryEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface StoriesDao {
    @Query("SELECT * FROM stories")
    fun getAllStories() : Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE isFavorite = 1")
    fun getFavoriteStories() : Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(story : List<StoryEntity>)

    @Update
    fun updateFavoriteStories(story : StoryEntity)

}