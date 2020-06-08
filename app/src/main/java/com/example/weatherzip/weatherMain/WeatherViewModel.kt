package com.example.weatherzip.weatherMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherzip.data.WeatherResponse
import com.example.weatherzip.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class WeatherApiStatus {
    LOADING,
    ERROR,
    DONE,
    ERROR_API_KEY_MISSING
}

class WeatherViewModel : ViewModel() {
    companion object {
        //Zip Code to get Weather for
        //TODO: Convert to input Text
        private const val zipCode: String = "45202"
    }

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<WeatherApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<WeatherApiStatus>
        get() = _status

    // Create LiveData variables for views

    private val _city = MutableLiveData<String>()
    val city: LiveData<String>
        get() = _city

    private val _state = MutableLiveData<String>()

    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String>
        get() = _temperature

    private val _weatherDesc = MutableLiveData<String>()
    val weatherDesc: LiveData<String>
        get() = _weatherDesc

    private val _iconLink = MutableLiveData<String>()
    val iconLink: LiveData<String>
        get() = _iconLink


    init {
        getWeather(zipCode)
        _city.value = "Cincinnati"
        _state.value = "Ohio"
        _temperature.value = "72"
        _weatherDesc.value = "Bright, Sunny"
    }

    /**
     * Gets Weather information from the Weather API Retrofit service and updates the
     * [LiveData]. The Retrofit service returns a coroutine Deferred, which we
     * await to get the result of the transaction.
     */
    private fun getWeather(zipCode: String) {

        WeatherApi.retroFitService.getWeatherAsync(zipCode = zipCode)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    _status.value = WeatherApiStatus.ERROR
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    response.body()?.let {
                        _status.value = WeatherApiStatus.LOADING
                        createListForView(it)
                        _status.value = WeatherApiStatus.DONE
                    }
                }
            })
    }

    private fun createListForView(weatherResponse: WeatherResponse) {
        _temperature.value = weatherResponse.temperature.dayTemperature.toString()
        _city.value = weatherResponse.cityName
        _iconLink.value = weatherResponse.weather[0].icon
        _weatherDesc.value = weatherResponse.weather[0].description
    }
}
