package com.example.lowescodetest.repository

import com.example.lowescodetest.model.ApiResponse
import com.example.lowescodetest.model.remote.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryImpl(
    private val api: Api
) : Repository {

    override suspend fun getWeatherFromCity(city: String): Flow<PresentationData> = flow {
        val response = api.getWeather(city)
        if (response.isSuccessful && response.body() != null) {
            emit(presentationDataFactoryResponse(response.body()!!))
        } else {
            emit(presentationDataFactoryError(response.message()))
        }
    }.flowOn(Dispatchers.IO)


    private fun presentationDataFactoryError(message: String): PresentationData {
        return PresentationData.Error(message)
    }

    private fun presentationDataFactoryResponse(dataResponse: ApiResponse): PresentationData {
        return PresentationData.Response(dataResponse)
    }

}
