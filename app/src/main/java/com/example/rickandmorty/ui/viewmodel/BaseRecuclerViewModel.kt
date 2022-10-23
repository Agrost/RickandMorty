package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.ui.EMPTY_STRING
import com.example.rickandmorty.ui.base.BaseViewModel

abstract class BaseRecyclerViewModel : BaseViewModel() {

    protected var pageNumber = 1

    protected var personName = EMPTY_STRING

    protected fun setDefaultPage() { pageNumber = DEFAULT_PAGE_NUMBER }

    protected fun setNextPage() { pageNumber++ }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1
    }
}