package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.room.dao.CharacterListDao
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.toCharacter
import com.example.rickandmorty.data.toFavoriteDto
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SavedViewModel @Inject constructor(
    private val characterListDao: CharacterListDao,
    private val favoriteListDao: FavoriteListDao,
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val _answer: MutableLiveData<Answer> = MutableLiveData(Answer.Loading)
    val answer: LiveData<Answer> get() = _answer

    init {
        getData()
    }

    fun getData() {
        setObserver(characterListDao.getCharacters())
    }

    fun getDataByName(name: String) {
        setObserver(characterListDao.selectByName(name))
    }

    fun addToFavorite(character: Character) {
        Completable.fromAction {
            favoriteListDao.insert(character.toFavoriteDto())
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun setObserver(observable: Single<List<CharacterEntity>>) {
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        this._answer.value = Answer.Success(it.toCharacter())
                    },
                    {
                        _answer.value = Answer.Failure()
                    },
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}