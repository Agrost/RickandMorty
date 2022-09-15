package com.example.rickandmorty.ui.activity.interactor

import com.example.rickandmorty.domain.entity.Person

interface FragmentInteractor {
    fun openImageFragment(person: Person)
}