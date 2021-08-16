package com.example.lowescodetest.repository

import com.example.lowescodetest.model.ApiResponse

sealed class PresentationData{
    data class Response(val data: ApiResponse): PresentationData()
    data class Error(val error: String): PresentationData()
}
