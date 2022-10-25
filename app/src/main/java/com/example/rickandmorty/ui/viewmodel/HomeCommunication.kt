package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.Communication
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState.Loading
import com.example.rickandmorty.ui.viewmodel.PersonListFragmentsState.Success
import javax.inject.Inject

interface ClearData {

    fun clearData()
}

class HomeCommunication @Inject constructor() : Communication<PersonListFragmentsState>, ClearData {

    private var personList = mutableListOf<Person>()

    private val mutableLiveData =
        MutableLiveData<PersonListFragmentsState>(Loading)

    override fun updateState(state: PersonListFragmentsState) {
        mutableLiveData.value = if (state is Success) {
            personList.addAll(state.data)
            Success(personList)
        } else {
            state
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<PersonListFragmentsState>) {
        mutableLiveData.observe(owner, observer)
    }

    override fun clearData() {
        personList = mutableListOf()
    }

    fun remove(person: Person) {
        personList.remove(person)
    }
}