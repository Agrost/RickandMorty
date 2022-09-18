package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.domain.usecase.GetPersonDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPersonDataUseCase: GetPersonDataUseCase
) : BaseRecyclerViewModel() {

    //    TODO
    var mutableList = mutableListOf<Person>()

    var mutableLiveData = MutableLiveData<List<Person>?>(null)

    fun test2() {
        viewModelScope.launch {
            test()
        }
    }

    suspend fun test() {
        mutableList.addAll(getPersonDataUseCase.getNextPage(""))
        mutableLiveData.value = mutableList
    }
}