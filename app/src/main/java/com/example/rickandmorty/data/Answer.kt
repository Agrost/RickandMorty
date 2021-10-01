package com.example.rickandmorty.data

import com.example.rickandmorty.domain.entity.Character

sealed class Answer {
    object Loading : Answer()
    class Success(
        val listCharacter: List<Character>
    ) : Answer()
    class Failure(
        val getNextPage: Boolean = false
    ) : Answer()
}