package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.MainFragmentBinding
import com.example.weatherapp.viewmodel.AppState
import com.example.weatherapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    //var _binding: MainFragmentBinding? = null
    lateinit var binding:MainFragmentBinding
    /*val binding
        get() : MainFragmentBinding {
            return _binding!!
        }*/

    companion object {
        fun newInstance(): Fragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //val observer = Observer<Any> { Toast.makeText(context, "Работает", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeather()
    }

    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Error -> TODO()
            is AppState.Success -> {
                //val weatherData = appState.dataWeather
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Success", Snackbar.LENGTH_LONG).show()
                setData(appState)
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun setData(appState: AppState.Success){
        binding.cityCoordinates.text = "${appState.dataWeather.city.lat} ${appState.dataWeather.city.long}"
        binding.cityName.text = appState.dataWeather.city.city
        binding.feelsLikeValue.text = appState.dataWeather.temperature.toString()
        binding.temperatureValue.text = appState.dataWeather.feelsLike.toString()
    }
}