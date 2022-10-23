package com.example.rickandmorty.data.remote.home

import com.example.rickandmorty.data.remote.dto.JsonResponseDto

interface HomeRemoteSource {
    suspend fun getJsonResponseDto(name: String, page: Int): JsonResponseDto
}