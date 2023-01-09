package com.hafidmust.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hafidmust.core.data.local.entity.StoryEntity


@Database(entities = [StoryEntity::class], version = 1, exportSchema = false)
abstract class StoriesDatabase : RoomDatabase() {
    abstract fun storiesDao(): StoriesDao
}