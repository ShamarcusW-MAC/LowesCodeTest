package com.example.lowescodetest.util

import android.widget.Toast
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Double.transFormToFarenheit(): Double{
    return ((this - 273.15) * 9/5 + 32)
}