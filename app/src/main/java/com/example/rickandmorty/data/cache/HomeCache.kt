package com.example.rickandmorty.data.cache

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.rxjava3.core.Single

interface HomeCache {
    fun getLastPage(): Int
    fun getLastSearchName(): String
    fun getHomeCache(): Single<Answer>
    fun setCharacterList(characterList: List<Character>, name: String)
    fun setNextPage(characterList: List<Character>)
}