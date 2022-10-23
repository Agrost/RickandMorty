package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.Communication
import javax.inject.Inject

interface ClearData {

    fun clearData()
}

class HomeCommunication @Inject constructor() : Communication<RecyclerFragmentsState>, ClearData {

    private var personList = mutableListOf<Person>()

    private val mutableLiveData =
        MutableLiveData<RecyclerFragmentsState>(RecyclerFragmentsState.Loading)

    override fun updateState(state: RecyclerFragmentsState) {
        mutableLiveData.value = if (state is RecyclerFragmentsState.Success) {
            personList.addAll(state.data)
            RecyclerFragmentsState.Success(personList)
        } else {
            state
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<RecyclerFragmentsState>) {
        mutableLiveData.observe(owner, observer)
    }

    override fun clearData() {
        personList = mutableListOf()
    }
}