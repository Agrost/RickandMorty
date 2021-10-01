package com.example.rickandmorty.data.cache

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeCacheImpl @Inject constructor() : HomeCache {

    private var characterList: List<Character>? = null
    private var lastPage = 1
    private var lastSearchName = ""

    override fun getLastPage(): Int = lastPage

    override fun getLastSearchName(): String = lastSearchName

    override fun getHomeCache(): Single<Answer> {
        return if (characterList != null) {
            Single.just(Answer.Success(characterList!!))
        } else {
            Single.just(Answer.Failure())
        }
    }

    override fun setCharacterList(characterList: List<Character>, name: String) {
        this.characterList = characterList
        lastPage = 2
        lastSearchName = name
    }

    override fun setNextPage(characterList: List<Character>) {
        val prevCharacterList = this.characterList?.toMutableList()
        prevCharacterList?.addAll(characterList)
        this.characterList = prevCharacterList
        lastPage++
    }
}