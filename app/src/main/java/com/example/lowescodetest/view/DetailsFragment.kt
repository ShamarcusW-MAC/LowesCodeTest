package com.example.lowescodetest.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lowescodetest.R
import com.example.lowescodetest.databinding.FragmentDetailsBinding
import com.example.lowescodetest.repository.PresentationData
import com.example.lowescodetest.util.transFormToFarenheit
import com.example.lowescodetest.viewmodel.WeatherViewModel

class DetailsFragment : Fragment() {

    companion object {
        private const val EXTRA_POSITION: String = "EXTRA_POSITION"

        fun newInstance(position: Int): DetailsFragment {
            val args = Bundle()
            args.putInt(EXTRA_POSITION, position)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var position: Int = 0
    private val viewModel: WeatherViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.getInt(EXTRA_POSITION)?.let {
            position = it
        }

        viewModel.weather.observe(viewLifecycleOwner) {
            updateDetails(it)
        }

        return binding.root
    }

    private fun updateDetails(it: PresentationData) {
        val dataSet = (it as PresentationData.Response).data.list[position]
        binding.detailDescription.text = dataSet.weather[0].description
        binding.detailType.text = dataSet.weather[0].main
        binding.detailTemperature.text = context?.getString(
            R.string.weather_temp_main,
            dataSet.main.temp.transFormToFarenheit()
        )
        binding.detailFeels.text = context?.getString(
            R.string.feels_like,
            dataSet.main.feels_like.transFormToFarenheit()
        )
    }
}