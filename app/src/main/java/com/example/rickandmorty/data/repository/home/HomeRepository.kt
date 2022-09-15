package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.domain.entity.Person
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    fun getData(): Single<List<Person>>
    fun getNextPage(name: String): Single<List<Person>>
}