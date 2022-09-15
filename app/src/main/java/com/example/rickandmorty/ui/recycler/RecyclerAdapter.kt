package com.example.rickandmorty.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.BaseRecyclerAdapter

class RecyclerAdapter(
    onItemClickListener: ((Int) -> Unit)? = null
) : BaseRecyclerAdapter<CharacterViewHolder, Person>(onItemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            PesronCardBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}