package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.data.PersonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommunicationRecycler : Communication<PersonState> {

    private val personState: MutableStateFlow<PersonState> = MutableStateFlow(PersonState.Loading)

    override fun getState() = personState.asStateFlow()

    override fun updateState(personState: PersonState) {
        this.personState.value = personState
    }
}