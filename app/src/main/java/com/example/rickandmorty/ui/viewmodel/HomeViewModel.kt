package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.data.room.dao.CharacterListDao
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.toCharacterDto
import com.example.rickandmorty.data.toFavoriteDto
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.usecase.GetHomeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val characterListDao: CharacterListDao,
    private val favoriteListDao: FavoriteListDao,
) : BaseViewModel() {

    private var listCharacter: MutableList<Character> = mutableListOf()

    init {
        getData()
    }

    override fun getData() {
        setObserver(getHomeUseCase.getData())
    }

    fun addToFavorite(character: Character) {
        compositeDisposable.add(
            Completable.fromAction {
                favoriteListDao.insert(character.toFavoriteDto())
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    override fun getDataByName(name: String) {
        setObserver(getHomeUseCase.getFirstPage(name))
    }

    private fun setObserver(observable: Single<Answer>) {
        compositeDisposable.add(
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it is Answer.Success) {
                            addToPersonDatabase(it.listCharacter)
                            listCharacter = it.listCharacter.toMutableList()
                        }
                        _answer.value = it
                    },
                    {
                        _answer.value = Answer.Failure()
                    },
                )
        )
    }

    private fun addToPersonDatabase(listCharacter: List<Character>) {
        compositeDisposable.add(
            Completable.fromAction {
                characterListDao.insert(listCharacter.toCharacterDto())
            }.subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun getNextPage() {
        if (_answer.value is Answer.Loading) return
        _answer.value = Answer.Loading
        compositeDisposable.add(
            getHomeUseCase.getNextPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { answer ->
                        if (answer is Answer.Success) {
                            addCharacterList(answer)
                        } else {
                            this._answer.value = answer
                        }
                    },
                    {
                        _answer.value = Answer.Failure(getNextPage = true)
                    },
                )
        )
    }

    private fun addCharacterList(answer: Answer.Success) {
        listCharacter.addAll(answer.listCharacter)
        this._answer.value = Answer.Success(listCharacter)
        addToPersonDatabase(listCharacter)
    }
}