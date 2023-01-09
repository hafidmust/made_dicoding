package com.hafidmust.core.data.remote

import com.hafidmust.core.data.remote.network.ApiResponse
import com.hafidmust.core.data.remote.network.ApiService
import com.hafidmust.core.data.remote.response.ListStoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    suspend fun getAllStories(token : String) : Flow<ApiResponse<List<ListStoryItem>>>{
        return flow {
            try {
                val response = apiService.getList(token)
                val dataArray = response.listStory
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.listStory))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}