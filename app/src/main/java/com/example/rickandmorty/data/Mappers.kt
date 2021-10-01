package com.example.rickandmorty.data

import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import com.example.rickandmorty.domain.entity.Character


internal fun JsonResponseDto.toCharacterList(): List<Character> {
    return this.results.map {
        Character(
            id = it.id,
            name = it.name,
            imageSrc = it.image,
        )
    }
}

internal fun List<Character>.toCharacterDto(): List<CharacterEntity> {
    return this.map {
        CharacterEntity(
            tableId = it.id.toString() + it.name,
            id = it.id,
            name = it.name,
            imageSrc = it.imageSrc
        )
    }
}

internal fun List<CharacterEntity>.toCharacter(): List<Character> {
    return this.map {
        Character(
            id = it.id,
            name = it.name,
            imageSrc = it.imageSrc
        )
    }
}

internal fun Character.toFavoriteDto(): FavoriteEntity {
       return FavoriteEntity(
            tableId = this.id.toString() + this.name,
            id = this.id,
            name = this.name,
            imageSrc = this.imageSrc
        )
}

internal fun FavoriteEntity.toCharacter(): Character {
        return Character(
            id = this.id,
            name = this.name,
            imageSrc = this.imageSrc
        )
}

@JvmName("toCharacterFavoriteEntity")
internal fun List<FavoriteEntity>.toCharacter(): List<Character> {
    return this.map {
        Character(
            id = it.id,
            name = it.name,
            imageSrc = it.imageSrc
        )
    }
}