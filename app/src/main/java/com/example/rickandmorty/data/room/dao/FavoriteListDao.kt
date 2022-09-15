package com.example.rickandmorty.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteListDao {
    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun getFavorites(): Single<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM favorite_table WHERE name LIKE '%' || :search || '%' ORDER BY id ASC")
    fun selectByName(search: String): Single<List<FavoriteEntity>>
}
