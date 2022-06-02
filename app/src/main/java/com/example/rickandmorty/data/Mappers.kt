package com.example.rickandmorty.data

import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import com.example.rickandmorty.domain.entity.Character

interface Mapper<S, T> {

    fun map(source: S): T
}

class JsonResponseDtoToCharacterListMapper() : Mapper<JsonResponseDto, List<Character>> {

    override fun map(source: JsonResponseDto): List<Character> {
        return source.results.map {
            Character(
                id = it.id,
                name = it.name,
                imageSrc = it.image,
            )
        }
    }
}

class ListCharacterEntityToCharacterDtoMapper : Mapper<List<Character>, List<CharacterEntity>> {

    override fun map(source: List<Character>): List<CharacterEntity> {
        return source.map {
            CharacterEntity(
                tableId = it.id.toString() + it.name,
                id = it.id,
                name = it.name,
                imageSrc = it.imageSrc
            )
        }
    }
}

class ListCharacterEntityToCharacterListMapper : Mapper<List<CharacterEntity>, List<Character>> {

    override fun map(source: List<CharacterEntity>): List<Character> {
        return source.map {
            Character(
                id = it.id,
                name = it.name,
                imageSrc = it.imageSrc
            )
        }
    }
}

class CharacterToFavoriteEntityMapper : Mapper<Character, FavoriteEntity> {

    override fun map(source: Character): FavoriteEntity {
        return FavoriteEntity(
            tableId = source.id.toString() + source.name,
            id = source.id,
            name = source.name,
            imageSrc = source.imageSrc
        )
    }
}

class FavoriteEntityToCharacterListMapper : Mapper<List<FavoriteEntity>, List<Character>> {

    override fun map(source: List<FavoriteEntity>): List<Character> {
        return source.map {
            Character(
                id = it.id,
                name = it.name,
                imageSrc = it.imageSrc
            )
        }
    }
}