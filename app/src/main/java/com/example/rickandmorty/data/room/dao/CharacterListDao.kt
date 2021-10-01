package com.example.rickandmorty.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.room.entity.CharacterEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterListDao {
    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getCharacters(): Single<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characterEntityList: List<CharacterEntity>)

    @Query("SELECT * FROM character_table WHERE name LIKE '%' || :search || '%'  ORDER BY id ASC")
    fun selectByName(search: String): Single<List<CharacterEntity>>
}
