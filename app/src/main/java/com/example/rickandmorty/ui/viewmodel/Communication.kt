package com.example.rickandmorty.ui.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface Communication<T> {

    fun getState(): StateFlow<T>

    fun updateState(personState: T)
}