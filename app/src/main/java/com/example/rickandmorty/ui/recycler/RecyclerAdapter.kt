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
import com.example.rickandmorty.domain.entity.Character

class RecyclerAdapter(
    val openImageActivity: (Character) -> Unit,
    val addToFavorite: (Character) -> Unit,
    val deleteFromFavorite: (Int) -> Unit,
    val isFavorite: Boolean
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items: MutableList<Character> = mutableListOf()
        set(value) {
            val callback = DiffCallback(field, value)
            field = value
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pesron_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val idText = holder.itemView.context
            .getString(R.string.id_text, items[position].id.toString())
        holder.repoId.text = idText
        holder.repoName.text = items[position].name
        Glide.with(holder.characterImage.context)
            .load(items[position].imageSrc)
            .placeholder(R.drawable.animview)
            .into(holder.characterImage)
        holder.characterImage.setOnClickListener { openImageActivity(items[position]) }
        holder.addToFavoriteButton.isVisible = !isFavorite
        holder.addToFavoriteButton.setOnClickListener { addToFavorite(items[position]) }
        holder.deleteIcon.isVisible = isFavorite
        holder.deleteIcon.setOnClickListener {
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
            deleteFromFavorite(items[position].id)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var repoId: TextView = itemView.findViewById(R.id.character_id)
        var repoName: TextView = itemView.findViewById(R.id.character_name)
        var characterImage: ImageView = itemView.findViewById(R.id.character_image)
        var deleteIcon: ImageView = itemView.findViewById(R.id.delete_from_favorite)
        var addToFavoriteButton: Button = itemView.findViewById(R.id.add_to_favorite)
    }
}