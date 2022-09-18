package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.JsonResponseDtoToCharacterListMapper
import com.example.rickandmorty.data.cache.HomeCache
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import com.example.rickandmorty.domain.entity.Person
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeCache: HomeCache,
    private val homeRemoteSource: HomeRemoteSource
) : HomeRepository {

    override suspend fun getData(): List<Person> = homeCache.getPersonList()

    override suspend fun getNextPage(name: String): List<Person> {
        val response = homeRemoteSource.getJsonResponseDto(name, homeCache.getLastPage())
        val characterList = JsonResponseDtoToCharacterListMapper().map(response)
        homeCache.addPersonList(characterList)
        return characterList
    }
}

