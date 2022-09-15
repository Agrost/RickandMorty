package com.example.rickandmorty.data

import com.example.rickandmorty.data.remote.dto.JsonResponseDto
import com.example.rickandmorty.data.room.entity.CharacterEntity
import com.example.rickandmorty.data.room.entity.FavoriteEntity
import com.example.rickandmorty.domain.entity.Person

interface Mapper<S, T> {

    fun map(source: S): T
}

// TODO еще поработать над мапперами
class JsonResponseDtoToCharacterListMapper() : Mapper<JsonResponseDto, List<Person>> {

    override fun map(source: JsonResponseDto): List<Person> {
        return source.results.map {
            Person(
                id = it.id,
                name = it.name,
                imageSrc = it.image,
            )
        }
    }
}

class ListCharacterEntityToCharacterDtoMapper : Mapper<List<Person>, List<CharacterEntity>> {

    override fun map(source: List<Person>): List<CharacterEntity> {
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

class ListCharacterEntityToCharacterListMapper : Mapper<List<CharacterEntity>, List<Person>> {

    override fun map(source: List<CharacterEntity>): List<Person> {
        return source.map {
            Person(
                id = it.id,
                name = it.name,
                imageSrc = it.imageSrc
            )
        }
    }
}

class CharacterToFavoriteEntityMapper : Mapper<Person, FavoriteEntity> {

    override fun map(source: Person): FavoriteEntity {
        return FavoriteEntity(
            tableId = source.id.toString() + source.name,
            id = source.id,
            name = source.name,
            imageSrc = source.imageSrc
        )
    }
}

class FavoriteEntityToCharacterListMapper : Mapper<List<FavoriteEntity>, List<Person>> {

    override fun map(source: List<FavoriteEntity>): List<Person> {
        return source.map {
            Person(
                id = it.id,
                name = it.name,
                imageSrc = it.imageSrc
            )
        }
    }
}