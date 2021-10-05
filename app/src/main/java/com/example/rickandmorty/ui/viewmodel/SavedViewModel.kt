package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.room.dao.CharacterListDao
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.toCharacterList
import com.example.rickandmorty.data.toFavoriteDto
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SavedViewModel @Inject constructor(
    private val characterListDao: CharacterListDao,
    private val favoriteListDao: FavoriteListDao,
) : BaseViewModel() {

    init {
        getData()
    }

    override fun getDataByName(name: String) {
        setObserver(characterListDao.selectByName(name))
    }

    override fun getData() {
        setObserver(characterListDao.getCharacters())
    }

    fun addToFavorite(character: Character) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteListDao.insert(character.toFavoriteDto())
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    private fun setObserver(observable: Single<List<CharacterEntity>>) {
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        this._answer.value = Answer.Success(it.toCharacterList())
                    },
                    {
                        _answer.value = Answer.Failure()
                    },
                )
        )
    }
}