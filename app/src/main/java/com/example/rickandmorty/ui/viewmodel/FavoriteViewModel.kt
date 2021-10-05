package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.favoriteToCharacterList
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteListDao: FavoriteListDao,
) : BaseViewModel() {

    private var listCharacter: MutableList<Character> = mutableListOf()

    init {
        getData()
    }

    override fun getData() {
        setObserver(favoriteListDao.getFavorites())
    }

    override fun getDataByName(name: String) {
        setObserver(favoriteListDao.selectByName(name))
    }

    private fun setObserver(observable: Single<List<FavoriteEntity>>) {

        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        listCharacter = it.favoriteToCharacterList().toMutableList()
                        this._answer.value = Answer.Success(it.favoriteToCharacterList())
                    },
                    {
                        _answer.value = Answer.Failure()
                    },
                )
        )
    }

    fun deleteFromFavorite(id: Int) {
        listCharacter.removeAll { it.id == id }
        this._answer.value = Answer.Success(listCharacter)
        compositeDisposable.add(
            Completable.fromAction {
                favoriteListDao.deleteById(id)
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }
}