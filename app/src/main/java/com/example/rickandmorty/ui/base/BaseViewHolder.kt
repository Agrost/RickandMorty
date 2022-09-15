package com.example.rickandmorty.ui.base

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseViewHolder<T>(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bindData(data: T)

    protected val resources: Resources = binding.root.resources

    protected fun getString(@StringRes stringRes: Int) = resources.getString(stringRes)
}