package com.example.rickandmorty.data.repository.home

import com.example.rickandmorty.data.CharacterEntityListToPersonListMapper
import com.example.rickandmorty.data.JsonResponseDtoToCharacterListMapper
import com.example.rickandmorty.data.PersonListToCharacterEntityListMapper
import com.example.rickandmorty.data.remote.home.HomeRemoteSource
import com.example.rickandmorty.data.room.dao.CharacterListDao
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteSource: HomeRemoteSource,
    private val characterListDao: CharacterListDao
) : HomeRepository {

    override suspend fun getData(): List<Person> =
        CharacterEntityListToPersonListMapper().map(characterListDao.getCharacters())

    override suspend fun getNextPage(name: String, page: Int): PersonListFragmentsState {
        return try {
            val response = homeRemoteSource.getJsonResponseDto(name, page)
            val characterList = JsonResponseDtoToCharacterListMapper().map(response)
//            TODO не проходит загрузка в Room
//            characterListDao.insert(PersonListToCharacterEntityListMapper().map(characterList))
            PersonListFragmentsState.Success(characterList)
        } catch (e: Exception) {
            PersonListFragmentsState.Error()
        }
    }
}
