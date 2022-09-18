package com.example.rickandmorty.data.cache

import com.example.rickandmorty.domain.entity.Person
import javax.inject.Inject

class HomeCacheImpl @Inject constructor()  : HomeCache {

    private var personList: MutableList<Person> = mutableListOf()
    private var lastPage = 1

    override fun getLastPage(): Int = lastPage

    override fun getPersonList(): List<Person> {
        return personList.toList()
    }

    override fun addPersonList(personList: List<Person>) {
        this.personList.addAll(personList)
        lastPage++
    }
}