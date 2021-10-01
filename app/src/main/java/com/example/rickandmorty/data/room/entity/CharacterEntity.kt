package com.example.rickandmorty.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey
    val tableId: String,
    val id: Int,
    val name: String,
    val imageSrc: String,
)
