package com.example.rickandmorty.ui.recycler

import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.BaseViewHolder

class CharacterViewHolder(
    binding: PesronCardBinding
) : BaseViewHolder<Person>(binding) {

    override fun bindData(data: Person) {
        with(binding as PesronCardBinding) {
        }
    }
}