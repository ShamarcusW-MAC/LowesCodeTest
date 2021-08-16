package com.example.lowescodetest.model

data class ApiResponse(
    val city: City,
    val list: List<WeatherData>
)

data class City(
    val name: String
)

data class WeatherData(
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Float,
    val visibility: Int,
    val weather: List<Weather>,
)

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)