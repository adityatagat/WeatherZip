package com.example.weatherzip.weatherMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherzip.R
import com.example.weatherzip.databinding.WeatherFragmentBinding


class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.weather_fragment, container, false
        )
        //Setup direct access from view to viewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        //Setup viewModel as a data binding variable in the layout XML to directly access it
        binding.weatherViewModel = viewModel

        //Set lifeCycleOwner to the fragment to allow layout to auto-update from LiveData objects
        binding.lifecycleOwner = this

        return binding.root
    }
}
