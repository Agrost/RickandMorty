package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.data.repository.home.HomeRepository
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    fun getData() = homeRepository.getData()
    fun getNextPage(name: String) = homeRepository.getNextPage(name)
}