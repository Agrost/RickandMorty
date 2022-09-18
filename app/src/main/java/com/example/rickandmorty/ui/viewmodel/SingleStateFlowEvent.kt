package com.example.rickandmorty.ui.viewmodel

import java.util.concurrent.atomic.AtomicBoolean

class Event<T>(private val data: T) {

    private val hasBeenTake = AtomicBoolean(false)

    //TODO когда будет понятна реализация, надо будет убрать null и сделать через observe
    fun getData(): T? {
        if (hasBeenTake.compareAndSet(true, false)) {
            return data
        }
        return null
    }
}