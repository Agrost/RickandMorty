package com.example.rickandmorty.data

import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/character/")
    fun getJsonResponse(
        @Query("name") name: String,
        @Query("page") page: Int
    ): Single<JsonResponseDto>
}