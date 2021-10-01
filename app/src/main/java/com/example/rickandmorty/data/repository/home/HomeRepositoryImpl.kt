package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.cache.HomeCache
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import com.example.rickandmorty.data.toCharacterList
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeCache: HomeCache,
    private val homeRemoteSource: HomeRemoteSource
) : HomeRepository {

    override fun getData(): Single<Answer> {
        return homeCache.getHomeCache().flatMap {
            if (it is Answer.Failure) {
                getFirstPage()
            } else {
                Single.just(it)
            }
        }
    }

    override fun getFirstPage(name: String): Single<Answer> {
        return homeRemoteSource.getJsonResponseDto(name, 1)
            .map { homeCache.setCharacterList(it.toCharacterList(), name) }
            .flatMap { homeCache.getHomeCache() }
    }

    override fun getNextPage(): Single<Answer> {
        val lastSearchName = homeCache.getLastSearchName()
        val lastPage = homeCache.getLastPage()
        return homeRemoteSource.getJsonResponseDto(lastSearchName, lastPage)
            .map {
                val characterList = it.toCharacterList()
                homeCache.setNextPage(characterList)
                Answer.Success(characterList)
            }
    }
}

