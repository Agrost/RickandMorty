package com.example.rickandmorty.data.remote.home

import com.example.rickandmorty.data.Api
import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeRemoteSourceImpl @Inject constructor(
    private val api: Api
) : HomeRemoteSource {
    override fun getJsonResponseDto(name: String, page: Int): Single<JsonResponseDto> {
        return api.getJsonResponse(name, page)
    }
}