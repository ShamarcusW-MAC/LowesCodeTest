package com.example.lowescodetest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lowescodetest.R
import com.example.lowescodetest.databinding.ItemLayoutBinding
import com.example.lowescodetest.model.WeatherData
import com.example.lowescodetest.util.transFormToFarenheit

class WeatherAdapter(private val click: (Int) -> Unit) :
    ListAdapter<WeatherData, WeatherAdapter.WeatherViewHolder>(DiffCallBack) {

    class WeatherViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dataItem: WeatherData, click: (Int) -> Unit) {
            binding.weatherDescription.text = dataItem.weather[0].description
            binding.weatherTemperature.text = binding.root.context.getString(
                R.string.weather_temp,
                dataItem.main.temp.transFormToFarenheit()
            )
            binding.root.setOnClickListener { click(adapterPosition) }
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<WeatherData>() {
        override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(currentList[position]){ click(it) }
    }
}