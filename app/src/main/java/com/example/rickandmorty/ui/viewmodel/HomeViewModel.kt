package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.usecase.GetPersonDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val communication: HomeCommunication,
    private val getPersonDataUseCase: GetPersonDataUseCase.Base
) : BaseRecyclerViewModel() {

    init {
        communication.updateState(RecyclerFragmentsState.Loading)
        loadData()
    }

    fun observe(owner: LifecycleOwner, observer: Observer<RecyclerFragmentsState>) {
        communication.observe(owner, observer)
    }

    fun getPersonsOnSearch(name: String) {
        communication.clearData()
        communication.updateState(RecyclerFragmentsState.Update)
        personName = name
        setDefaultPage()
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val state = getPersonDataUseCase.getNextPage(personName, pageNumber)
            communication.updateState(state)
            setNextPage()
        }

    }
}