package com.example.rickandmorty.data.remote.home

import com.example.rickandmorty.data.Api
import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import javax.inject.Inject

class HomeRemoteSourceImpl @Inject constructor(
    private val api: Api
) : HomeRemoteSource {
    override fun getJsonResponseDto(name: String, page: Int): Single<JsonResponseDto> {
        return api.getJsonResponse(name, page)
    }
}