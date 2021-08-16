package com.example.lowescodetest.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    // Use cases
    suspend fun getWeatherFromCity(city: String): Flow<PresentationData>
}