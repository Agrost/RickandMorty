package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.data.repository.home.HomeRepository
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState
import javax.inject.Inject

interface GetPersonDataUseCase {

    suspend fun getData(): List<Person>

    suspend fun getNextPage(name: String, page: Int): PersonListFragmentsState

    class Base @Inject constructor(private val homeRepository: HomeRepository) :
        GetPersonDataUseCase {

        override suspend fun getData() = homeRepository.getData()

        override suspend fun getNextPage(name: String, page: Int) =
            homeRepository.getNextPage(name, page)
    }
}