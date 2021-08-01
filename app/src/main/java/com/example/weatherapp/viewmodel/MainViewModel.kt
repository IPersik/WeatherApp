package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Repository
import com.example.weatherapp.model.RepositoryImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getWeather() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        val i: Int = 3
        val random = Random(3).nextInt()
        when (random) {
            1 -> {liveDataObserver.postValue(AppState.Loading)
            }
            2 -> {
                liveDataObserver.postValue(AppState.Success(repository.getWeatherFromLocal()))
            }
            3 -> {
                // error
            }
        }
        Thread {
            sleep(1000)
        }.start()
    }
}