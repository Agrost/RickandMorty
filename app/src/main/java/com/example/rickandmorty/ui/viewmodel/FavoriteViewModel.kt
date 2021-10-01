package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.favoriteToCharacterList
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteListDao: FavoriteListDao,
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val _answer: MutableLiveData<Answer> = MutableLiveData(Answer.Loading)
    val answer: LiveData<Answer> get() = _answer

    init {
        getData()
    }

    private fun getData() {
        setObserver(favoriteListDao.getFavorites())
    }

    fun getDataByName(name: String) {
        setObserver(favoriteListDao.selectByName(name))
    }

    private fun setObserver(observable: Single<List<FavoriteEntity>>) {
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        this._answer.value = Answer.Success(it.favoriteToCharacterList())
                    },
                    {
                        _answer.value = Answer.Failure()
                    },
                )
        )
    }

    fun deleteFromFavorite(id: Int) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteListDao.deleteById(id)
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}