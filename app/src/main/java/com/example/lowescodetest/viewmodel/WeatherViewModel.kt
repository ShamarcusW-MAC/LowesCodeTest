package com.example.lowescodetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lowescodetest.model.remote.Api
import com.example.lowescodetest.repository.PresentationData
import com.example.lowescodetest.repository.Repository
import com.example.lowescodetest.repository.RepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class WeatherViewModel : ViewModel() {

    private val repository: Repository by lazy {
        RepositoryImpl(Api.getApi())
    }

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private val _weather = MutableLiveData<PresentationData>()
    val weather: LiveData<PresentationData> = _weather

    override fun onCleared() {
        super.onCleared()
        scope.cancel("ViewModel cleared")
    }

    fun getCityByNameWeather(cityName: String) {

        scope.launch {
            repository.getWeatherFromCity(cityName)
                .collect {
                    _weather.value = it
                }
        }
    }

}