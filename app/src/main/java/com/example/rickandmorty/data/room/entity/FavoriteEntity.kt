package com.example.rickandmorty.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    val tableId: String,
    val id: Int,
    val name: String,
    val imageSrc: String,
)
