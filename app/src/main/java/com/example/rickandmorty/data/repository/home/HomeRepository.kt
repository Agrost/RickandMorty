package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.domain.entity.Person

interface HomeRepository {
    suspend fun getData(): List<Person>
    suspend fun getNextPage(name: String): List<Person>
}