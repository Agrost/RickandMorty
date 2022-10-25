package com.example.rickandmorty.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.BaseRecyclerAdapter

class PersonAdapter(
    private val onDeleteClickListener: ((Person) -> Unit),
    private val onLikeClickListener: ((Person) -> Unit),
    private val onPhotoClickListener: ((String) -> Unit),
    private val isNotFavorite: Boolean = false
) : BaseRecyclerAdapter<CharacterViewHolder, Person>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            onDeleteClickListener = { person, position -> onDelete(person, position)},
            onLikeClickListener,
            onPhotoClickListener,
            isNotFavorite,
            PesronCardBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private fun onDelete(person: Person, position: Int) {
        notifyItemRemoved(position)
        onDeleteClickListener(person)
    }
}