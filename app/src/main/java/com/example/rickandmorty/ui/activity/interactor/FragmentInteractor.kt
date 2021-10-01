package com.example.rickandmorty.ui.activity.interactor

import com.example.rickandmorty.domain.entity.Character

interface FragmentInteractor {
    fun openImageFragment(character: Character)
}