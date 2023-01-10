package com.hafidmust.core.di

import android.content.Context

import androidx.room.Room
import com.hafidmust.core.data.local.room.StoriesDao
import com.hafidmust.core.data.local.room.StoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase : ByteArray = SQLiteDatabase.getBytes("stories".toCharArray())
    private val factory = SupportFactory(passphrase)
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : StoriesDatabase = Room.databaseBuilder(
        context, StoriesDatabase::class.java, "Stories.db"
    ).fallbackToDestructiveMigration()
        .openHelperFactory(factory)
        .build()

    @Provides
    fun provideStoriesDao(database : StoriesDatabase) : StoriesDao = database.storiesDao()
}