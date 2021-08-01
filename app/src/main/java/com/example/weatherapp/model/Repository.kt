package com.example.weatherapp.model

import com.example.weatherapp.view.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocal(): Weather
}