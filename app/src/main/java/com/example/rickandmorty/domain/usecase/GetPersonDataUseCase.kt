package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.data.repository.home.HomeRepository
import javax.inject.Inject

class GetPersonDataUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun getData() = homeRepository.getData()
    suspend fun getNextPage(name: String) = homeRepository.getNextPage(name)
}