package com.example.rickandmorty.di.saved

import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.fragment.SavedFragment
import dagger.Subcomponent

@Subcomponent(modules = [SavedViewModelModule::class])
@FragmentScope
interface SavedComponent {

    fun inject(savedFragment: SavedFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SavedComponent
    }
}