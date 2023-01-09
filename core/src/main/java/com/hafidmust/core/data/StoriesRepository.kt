package com.hafidmust.core.data


import com.hafidmust.core.data.local.LocalDataSource
import com.hafidmust.core.data.remote.RemoteDataSource
import com.hafidmust.core.data.remote.network.ApiResponse
import com.hafidmust.core.data.remote.response.ListStoryItem
import com.hafidmust.core.domain.model.Stories
import com.hafidmust.core.domain.repository.IStoriesRepository
import com.hafidmust.core.utils.AppExecutors
import com.hafidmust.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoriesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IStoriesRepository {
    override fun getAllStories(token: String): Flow<com.hafidmust.core.data.Resource<List<Stories>>> =
        object : com.hafidmust.core.data.NetworkBoundResource<List<Stories>, List<ListStoryItem>>() {
            override fun loadFromDB(): Flow<List<Stories>> {
                return localDataSource.getAllStories().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Stories>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ListStoryItem>>> {
                return remoteDataSource.getAllStories(token)
            }

            override suspend fun saveCallResult(data: List<ListStoryItem>) {
                val storyList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertStories(storyList)
            }
        }.asFlow()

    override fun getFavoriteStories(): Flow<List<Stories>> {
        return localDataSource.getFavoriteStories().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTourism(stories: Stories, state: Boolean) {
        val storyEntity = DataMapper.mapDomainToEntity(stories)
        appExecutors.diskIO().execute { localDataSource.setFavoriteStories(storyEntity, state) }
    }
}