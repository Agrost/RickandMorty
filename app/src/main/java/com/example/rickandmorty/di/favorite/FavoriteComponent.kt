package com.example.rickandmorty.di.favorite

import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.fragment.FavoriteFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavoriteViewModelModule::class])
@FragmentScope
interface FavoriteComponent {

    fun inject(favoriteFragment: FavoriteFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }
}