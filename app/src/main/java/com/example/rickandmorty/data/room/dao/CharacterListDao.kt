package com.example.rickandmorty.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.room.entity.CharacterEntity

@Dao
interface CharacterListDao {
    @Query("SELECT * FROM character_table ORDER BY id ASC")
    suspend fun getCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterEntityList: List<CharacterEntity>)

    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :search || '%'  ORDER BY id ASC")
    suspend fun selectByName(search: String): List<CharacterEntity>
}
