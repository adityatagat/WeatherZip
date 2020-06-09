package com.example.weatherzip

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weatherzip.weatherMain.WeatherApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, iconLink: String?) {
    iconLink?.let {
        val url: Uri? = iconLink.toUri().buildUpon()
            .appendQueryParameter("apiKey", BuildConfig.OPEN_WEATHER_API_KEY).build()
        Log.i("Glide URL", url.toString())
        Glide.with(imgView.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("weatherApiStatus")
fun bindApiStatus(statusImageView: ImageView, status: WeatherApiStatus?) {
    when (status) {
        WeatherApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        WeatherApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        WeatherApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        WeatherApiStatus.ERROR_API_KEY_MISSING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

    }
}