package com.example.rickandmorty.data.remote.dto

import com.google.gson.annotations.SerializedName


data class JsonResponseDto(
    @SerializedName("results") val results: List<Result>,
)
