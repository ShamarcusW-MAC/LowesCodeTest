package com.example.lowescodetest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowescodetest.MainActivity
import com.example.lowescodetest.databinding.FragmentWeatherBinding
import com.example.lowescodetest.model.ApiResponse
import com.example.lowescodetest.repository.PresentationData
import com.example.lowescodetest.util.showToast
import com.example.lowescodetest.viewmodel.WeatherViewModel

class WeatherFragment: Fragment() {

    companion object{
        private const val EXTRA_CITY_SEARCH: String = "EXTRA_CITY_SEARCH"

        fun newInstance(citySearch: String): WeatherFragment {
            val args = Bundle()
            args.putString(EXTRA_CITY_SEARCH, citySearch)
            val fragment = WeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: WeatherViewModel by activityViewModels()
    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding get() =  _binding!!
    private val adapter: WeatherAdapter by lazy {
        WeatherAdapter(::detailFragment)
    }

    private fun detailFragment(position: Int) {
        (activity as MainActivity)?.detailFragment(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel.weather.observe(viewLifecycleOwner){
            updateData(it)
        }
        arguments?.getString(EXTRA_CITY_SEARCH)?.let {
            viewModel.getCityByNameWeather(it)
            (activity as AppCompatActivity)?.supportActionBar?.title = it.uppercase()
            (activity as AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity)?.supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        }
        binding.weather.layoutManager = LinearLayoutManager(activity)
        binding.weather.adapter = adapter
        return binding.root
    }

    private fun updateData(it: PresentationData) {
        when (it){
            is PresentationData.Response -> adapter.submitList(it.data.list)
            is PresentationData.Error -> activity?.showToast(it.error)
        }
    }
}