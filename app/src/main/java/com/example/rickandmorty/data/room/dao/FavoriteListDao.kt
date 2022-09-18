package com.example.rickandmorty.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.room.entity.FavoriteEntity

@Dao
interface FavoriteListDao {
    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM favorite_table WHERE name LIKE '%' || :search || '%' ORDER BY id ASC")
    suspend fun selectByName(search: String): List<FavoriteEntity>
}
