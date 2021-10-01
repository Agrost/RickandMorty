package com.example.rickandmorty.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.room.dao.CharacterListDao
import com.example.rickandmorty.data.room.dao.FavoriteListDao
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.room.entity.FavoriteEntity

@Database(entities = [CharacterEntity::class, FavoriteEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterListDao

    abstract fun getFavoriteDao(): FavoriteListDao
}