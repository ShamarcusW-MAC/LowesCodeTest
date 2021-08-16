package com.example.lowescodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lowescodetest.view.DetailsFragment
import com.example.lowescodetest.view.SearchFragment
import com.example.lowescodetest.view.WeatherFragment

class MainActivity : AppCompatActivity() {
    companion object {
        private val FRAGMENT_WEATHER: String = "FRAGMENT_WEATHER"
        private val FRAGMENT_DETAIL: String = "FRAGMENT_DETAIL"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchFragment()
    }

    private fun searchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment())
            .commit()
    }

    fun detailFragment(position: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailsFragment.newInstance(position))
            .addToBackStack(FRAGMENT_DETAIL)
            .commit()
    }

    fun weatherFragment(citySearch: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WeatherFragment.newInstance(citySearch))
            .addToBackStack(FRAGMENT_WEATHER)
            .commit()
    }
}