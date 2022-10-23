package com.example.rickandmorty.ui.recycler

import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.BaseViewHolder

class CharacterViewHolder(
    binding: PesronCardBinding
) : BaseViewHolder<Person>(binding) {

    override fun bindData(data: Person) {
        val idText = itemView.context
            .getString(R.string.id_text, data.id.toString())

        with(binding as PesronCardBinding) {
            characterId.text = idText
            characterName.text = data.name
            Glide.with(characterImage.context)
                .load(data.imageSrc)
                .placeholder(R.drawable.animview)
                .into(characterImage)
        }
    }
}