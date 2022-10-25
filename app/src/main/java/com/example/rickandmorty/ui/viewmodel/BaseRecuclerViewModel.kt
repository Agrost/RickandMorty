package com.example.rickandmorty.ui.viewmodel

import com.example.rickandmorty.ui.EMPTY_STRING
import com.example.rickandmorty.ui.base.BaseViewModel

abstract class BasePersonListViewModel : BaseViewModel() {

    protected var pageNumber = DEFAULT_PAGE_NUMBER

    protected var personName = EMPTY_STRING

    protected fun setDefaultPage() { pageNumber = DEFAULT_PAGE_NUMBER }

    protected fun setNextPage() { pageNumber++ }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1
    }
}