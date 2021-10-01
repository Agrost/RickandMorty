package com.example.rickandmorty.di.home

import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.fragment.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeViewModelModule::class])
@FragmentScope
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
}