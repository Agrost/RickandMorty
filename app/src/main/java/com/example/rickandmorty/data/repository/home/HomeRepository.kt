package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.viewmodel.RecyclerFragmentsState

interface HomeRepository {
    suspend fun getData(): List<Person>
    suspend fun getNextPage(name: String, page: Int): RecyclerFragmentsState
}