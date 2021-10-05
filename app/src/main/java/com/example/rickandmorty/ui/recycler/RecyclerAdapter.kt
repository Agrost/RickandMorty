package com.example.rickandmorty.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.PesronCardBinding
import com.example.rickandmorty.domain.entity.Character

class RecyclerAdapter(
    val openImageActivity: (Character) -> Unit,
    val addToFavorite: (Character) -> Unit,
    val deleteFromFavorite: (Int) -> Unit,
    val isFavorite: Boolean
) : RecyclerView.Adapter<RecyclerAdapter.CharacterViewHolder>() {

    var items: MutableList<Character> = mutableListOf()
        set(value) {
            val callback = DiffCallback(field, value)
            field = value
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pesron_card, parent, false),
            openImageActivity,
            addToFavorite,
            isFavorite
        ) { position ->
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
            deleteFromFavorite(items[position].id)
        }
    }

    override fun onBindViewHolder(holderCharacter: CharacterViewHolder, position: Int) {
        holderCharacter.bindData(items[position])
    }

    class CharacterViewHolder(
        itemView: View,
        val openImageActivity: (Character) -> Unit,
        val addToFavorite: (Character) -> Unit,
        val isFavorite: Boolean,
        val deleteItemListener: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = PesronCardBinding.bind(itemView)

        fun bindData(character: Character) {
            val idText = itemView.context
                .getString(R.string.id_text, character.id.toString())
            with(binding) {
                characterId.text = idText
                characterName.text = character.name
                Glide.with(characterImage.context)
                    .load(character.imageSrc)
                    .placeholder(R.drawable.animview)
                    .into(characterImage)
                characterImage.setOnClickListener { openImageActivity(character) }
                addToFavorite.isVisible = !isFavorite
                addToFavorite.setOnClickListener { addToFavorite(character) }
                deleteFromFavorite.isVisible = isFavorite
                deleteFromFavorite.setOnClickListener {
                    deleteItemListener(absoluteAdapterPosition)
                }
            }
        }
    }
}