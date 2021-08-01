package com.example.weatherapp.viewmodel

import com.example.weatherapp.view.Weather

sealed class AppState {
    //data class Success(val dataWeather : Any):AppState()
    data class Success(val dataWeather : Weather):AppState()
    data class Error(val error:Throwable):AppState()
    object Loading:AppState()
}