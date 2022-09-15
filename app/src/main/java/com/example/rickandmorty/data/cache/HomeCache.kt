package com.example.rickandmorty.data.cache

import com.example.rickandmorty.domain.entity.Person

interface HomeCache {
    fun getLastPage(): Int
    fun getPersonList(): List<Person>
    fun addPersonList(personList: List<Person>)
}