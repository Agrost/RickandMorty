package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.Answer
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    fun getData(): Single<Answer>
    fun getFirstPage(name: String  = ""): Single<Answer>
    fun getNextPage(): Single<Answer>
}