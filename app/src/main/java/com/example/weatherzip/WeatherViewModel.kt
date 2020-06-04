package com.example.weatherzip

import android.graphics.drawable.Icon
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

//    companion object {
//        const val WEATHER_1_ICON_LINK = "https://weather.ls.hereapi.com/static/weather/icon/1.png"
//    }
    // Create LiveData objects for weather
    private val _city = MutableLiveData<String>()
            val city: LiveData<String>
            get() = _city

    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    private val _temperature = MutableLiveData<Int>()
    val temperature: LiveData<Int>
        get() = _temperature

    private val _weatherDesc = MutableLiveData<String>()
    val weatherDesc: LiveData<String>
        get() = _weatherDesc

//    private val _weatherIcon = MutableLiveData<Icon>()
//    val weatherIcon: LiveData<Icon>
//        get() = _weatherIcon
init {

        _city.value= "Cincinnati"
        _state.value = "Ohio"
        _temperature.value = 22
        _weatherDesc.value = "Bright, Sunny, Hot"
//        _weatherIcon.value = Icon.createWithResource(this.,R.drawable.ic_launcher_foreground)
}

}
