package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.PersonToFavoriteEntityMapper
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.domain.usecase.GetPersonDataUseCase
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState.Loading
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState.Update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val communication: HomeCommunication,
    private val getPersonDataUseCase: GetPersonDataUseCase.Base,
    private val favoriteListDao: FavoriteListDao
) : BasePersonListViewModel() {

    init {
        loadData()
    }

    fun observe(owner: LifecycleOwner, observer: Observer<PersonListFragmentsState>) {
        communication.observe(owner, observer)
    }

    fun getPersonsOnSearch(name: String) {
        communication.clearData()
        personName = name
        setDefaultPage()
        loadData(true)
    }

    fun loadData(isUpdate: Boolean = false) {
        viewModelScope.launch {
            communication.updateState(if (isUpdate) Update else Loading)
            val state = getPersonDataUseCase.getNextPage(personName, pageNumber)
            communication.updateState(state)
            setNextPage()
        }
    }

    fun like(person: Person) {
        viewModelScope.launch {
            favoriteListDao.insert(PersonToFavoriteEntityMapper().map(person))
        }
    }

    fun delete(person: Person) {
        communication.remove(person)
    }
}