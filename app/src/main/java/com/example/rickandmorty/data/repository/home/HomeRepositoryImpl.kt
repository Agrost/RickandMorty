package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.JsonResponseDtoToCharacterListMapper
import com.example.rickandmorty.data.cache.HomeCache
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import com.example.rickandmorty.domain.entity.Person
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeCache: HomeCache,
    private val homeRemoteSource: HomeRemoteSource
) : HomeRepository {

    override fun getData(): Single<List<Person>> {
        return Single.just(homeCache.getPersonList())
    }

    override fun getNextPage(name: String): Single<List<Person>> {
        return homeRemoteSource.getJsonResponseDto(name, homeCache.getLastPage())
            .map {
                homeCache.addPersonList(JsonResponseDtoToCharacterListMapper().map(it))
            }
            .flatMap { Single.just(homeCache.getPersonList()) }
    }
}

