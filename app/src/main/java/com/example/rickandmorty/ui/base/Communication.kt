package com.example.rickandmorty.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication<T> {

    fun updateState(state: T)

    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}