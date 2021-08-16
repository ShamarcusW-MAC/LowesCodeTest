package com.example.lowescodetest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lowescodetest.MainActivity
import com.example.lowescodetest.databinding.FragmentSearchLayoutBinding
import com.example.lowescodetest.util.showToast
import com.example.lowescodetest.viewmodel.WeatherViewModel

class SearchFragment: Fragment() {

    private var _binding: FragmentSearchLayoutBinding? = null
    private val binding: FragmentSearchLayoutBinding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSearchLayoutBinding.inflate(inflater,
            container,
            false)

        binding.buttonSearch.setOnClickListener(::clickEvent)

        (activity as AppCompatActivity)?.supportActionBar?.hide()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity)?.supportActionBar?.show()
    }

    private fun clickEvent(view: View?) {
        if (binding.searchCity.text.toString().isNotEmpty())
            (activity as MainActivity)?.weatherFragment(binding.searchCity.text.toString())
        else
            activity?.showToast("No empty city search")
    }
}