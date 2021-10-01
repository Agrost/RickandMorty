package com.example.rickandmorty.data.remote.home

import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import io.reactivex.rxjava3.core.Single

interface HomeRemoteSource {
    fun getJsonResponseDto(name: String, page: Int): Single<JsonResponseDto>
}