package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Repository
import com.example.weatherapp.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserver : MutableLiveData<AppState> = MutableLiveData(),
                    val repository: Repository=RepositoryImpl()) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getWeather() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Loading)
            sleep(1000)
            liveDataObserver.postValue(AppState.Success(repository.getWeatherFromLocal()))
        }.start()
    }
}