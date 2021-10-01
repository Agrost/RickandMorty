package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.di.favorite.FavoriteComponent
import com.example.rickandmorty.di.home.HomeComponent
import com.example.rickandmorty.di.saved.SavedComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RetrofitModule::class, CacheModule::class, RoomDatabaseModule::class])
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun registerHomeComponent(): HomeComponent.Factory

    fun registerFavoriteComponent(): FavoriteComponent.Factory

    fun registerSavedComponent(): SavedComponent.Factory
}