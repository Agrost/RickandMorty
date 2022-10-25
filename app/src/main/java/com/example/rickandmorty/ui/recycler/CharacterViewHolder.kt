package com.example.rickandmorty.ui.recycler

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.base.BaseViewHolder

class CharacterViewHolder(
    private val onDeleteClickListener: (Person, Int) -> Unit,
    private val onLikeClickListener: (Person) -> Unit,
    private val onPhotoClickListener: (String) -> Unit,
    private val isNotFavorite: Boolean,
    binding: PesronCardBinding
) : BaseViewHolder<Person>(binding) {

    override fun bindData(data: Person) {
        val idText = resources.getString(R.string.id_text, data.id.toString())

        with(binding as PesronCardBinding) {
            pcIvLike.isVisible = isNotFavorite
            pcTvId.text = idText
            pcTvName.text = data.name
            Glide.with(pcIvPhoto.context)
                .load(data.imageSrc)
                .placeholder(R.drawable.animview)
                .into(pcIvPhoto)

            layoutPosition
            pcIvDelete.setOnClickListener { onDeleteClickListener(data, layoutPosition) }
            pcIvLike.setOnClickListener { onLikeClickListener(data) }
            pcIvPhoto.setOnClickListener { onPhotoClickListener(data.imageSrc) }
        }
    }
}