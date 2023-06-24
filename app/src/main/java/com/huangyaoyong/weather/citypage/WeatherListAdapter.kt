package com.huangyaoyong.weather.citypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huangyaoyong.weather.citypage.source.Cast
import com.huangyaoyong.weather.databinding.ItemCityWeatherForecastBinding

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/22
 * @desc:
 */
class WeatherListAdapter() : ListAdapter<WeatherItem, ForecastViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ItemCityWeatherForecastBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }
}

class ForecastViewHolder(private val binding: ItemCityWeatherForecastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(forecastItem: WeatherItem) {
        binding.label.text =
            if (forecastItem.type == TYPE_TODAY) "Today" else forecastItem.cast.date
        binding.tvLabelDayTemp.text = forecastItem.cast.daytemp
        binding.tvLabelDayWeather.text = forecastItem.cast.dayweather
        binding.tvLabelNightTemp.text = forecastItem.cast.nighttemp
        binding.tvLabelNightWeather.text = forecastItem.cast.nightweather
    }
}


private class DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem) = false

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem) = false
}

const val TYPE_TODAY = 0
const val TYPE_FORECAST = 1

class WeatherItem(val type: Int, val cast: Cast) {
    companion object {
        fun createTodayItem(cast: Cast) = WeatherItem(TYPE_TODAY, cast)
        fun createForecastItem(cast: Cast) = WeatherItem(TYPE_FORECAST, cast)
    }
}

