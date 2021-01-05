package com.funcode.core.data.source.remote

import android.util.Log
import com.funcode.core.data.source.remote.network.APIConfig
import com.funcode.core.data.source.remote.network.APIService
import com.funcode.core.data.source.remote.network.ApiResponse
import com.funcode.core.data.source.remote.response.Movie
import com.funcode.core.data.source.remote.response.TvShow
import com.funcode.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: APIService) {

    suspend fun getAllMovie(page: Int): Flow<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getAllMovie(page, APIConfig.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSources", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllTvShow(page: Int): Flow<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getAllTvShow(page, APIConfig.API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSources", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}