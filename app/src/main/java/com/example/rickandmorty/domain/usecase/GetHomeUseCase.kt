package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.data.repository.home.HomeRepository
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    fun getData() = homeRepository.getData()
    fun getFirstPage(name: String) = homeRepository.getFirstPage(name)
    fun getNextPage() = homeRepository.getNextPage()
}