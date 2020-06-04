package com.example.weatherzip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherzip.databinding.WeatherFragmentBinding
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : Fragment() {


    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.weather_fragment, container, false
        )
        //Setup direct access from view to viewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        //Setup viewModel as a data binding variable in the layout XML to directly access it
        binding.weatherViewModel = viewModel

        //Set lifeCycleOwner to the fragment to allow layout to auto-update from LiveData objects
        binding.lifecycleOwner = this

        viewModel.city.observe(viewLifecycleOwner, Observer { newCity ->
            binding.cityText.text = newCity
        })

        viewModel.state.observe(viewLifecycleOwner, Observer { newState ->
            binding.stateText.text = newState
        })

        viewModel.weatherDesc.observe(viewLifecycleOwner, Observer { newDesc ->
            binding.weatherDesc.text = newDesc
        })

//        viewModel.temperature.observe(viewLifecycleOwner, Observer { newTemp ->
//            binding.temperature.text = newTemp.toString() + " °C"
//        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Setup direct access from view to viewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
    }
}
