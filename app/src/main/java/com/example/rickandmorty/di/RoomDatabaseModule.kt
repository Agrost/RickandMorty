package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.room.CharacterDatabase
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "library_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesCharacterDAO(characterDatabase: CharacterDatabase) = characterDatabase.getCharacterDao()

    @Singleton
    @Provides
    fun providesFavoriteDAO(characterDatabase: CharacterDatabase) = characterDatabase.getFavoriteDao()
}