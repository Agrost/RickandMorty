package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Answer
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val _answer: MutableLiveData<Answer> = MutableLiveData(Answer.Loading)
    val answer: LiveData<Answer> get() = _answer

    abstract fun getData()

    abstract fun getDataByName(name: String)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}