package com.example.rickandmorty.ui.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T> : DiffUtil.Callback() {

    protected var oldList = listOf<T>()
    protected var newList = listOf<T>()

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    fun setLists(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun areContentsTheSame(oldCard: Int, newCard: Int): Boolean {
        return oldList[oldCard] == newList[newCard]
    }
}