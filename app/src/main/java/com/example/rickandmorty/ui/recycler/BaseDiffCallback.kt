package com.example.rickandmorty.ui.recycler

import com.example.rickandmorty.ui.base.BaseDiffCallback
import com.example.rickandmorty.domain.entity.Person

class CharacterDiffCallback() : BaseDiffCallback<Person>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}