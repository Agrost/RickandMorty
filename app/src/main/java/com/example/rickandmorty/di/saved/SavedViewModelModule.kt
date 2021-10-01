package com.example.rickandmorty.di.saved

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.di.keys.ViewModelKey
import com.example.rickandmorty.di.scope.FragmentScope
import com.example.rickandmorty.ui.viewmodel.SavedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SavedViewModelModule {

    @ViewModelKey(SavedViewModel::class)
    @FragmentScope
    @IntoMap
    @Binds
    abstract fun bindsSavedViewModule(
        favoriteViewModel: SavedViewModel
    ): ViewModel
}