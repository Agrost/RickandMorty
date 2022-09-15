package com.example.rickandmorty.data

import com.example.rickandmorty.domain.entity.Person

//TODO переработать
sealed interface PersonState {
    object Loading : PersonState
    class Success(val listPerson: List<Person>) : PersonState
    class Failure(val getNextPage: Boolean = false) : PersonState
}