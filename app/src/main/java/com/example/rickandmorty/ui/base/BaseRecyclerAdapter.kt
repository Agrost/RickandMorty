package com.example.rickandmorty.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : BaseViewHolder<T>, T>(
    private val onItemClickListener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<VH>() {

    private var diffUtilCallback: BaseDiffCallback<T>? = null

    fun setupDiffUtil(diffUtilCallback: BaseDiffCallback<T>) {
        this.diffUtilCallback = diffUtilCallback
    }

    var items: MutableList<T> = mutableListOf()
        //TODO посмотреть можно ли избавиться от NotifyDataSetChanged
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            diffUtilCallback?.let {
                it.setLists(field, value)
                field = value
                DiffUtil.calculateDiff(it).dispatchUpdatesTo(this)
            } ?: run {
                items = value
                notifyDataSetChanged()
            }
        }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(items[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int = items.count()

    fun getItem(orderNumber: Int) = items[orderNumber]
}