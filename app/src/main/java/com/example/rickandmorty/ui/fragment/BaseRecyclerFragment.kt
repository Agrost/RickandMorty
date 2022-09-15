package com.example.rickandmorty.ui.fragment

import androidx.annotation.LayoutRes
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.databinding.RecyclerFragmentBinding
import com.example.rickandmorty.ui.activity.interactor.FragmentInteractor
import com.example.rickandmorty.ui.base.BaseFragment
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import com.example.rickandmorty.ui.viewmodel.BaseRecyclerViewModel

abstract class BaseRecyclerFragment<VM : BaseRecyclerViewModel>(@LayoutRes contentLayoutId: Int) :
    BaseFragment<VM>(contentLayoutId) {

    protected var recyclerAdapter: RecyclerAdapter? = null

    protected val binding by viewBinding(RecyclerFragmentBinding::bind)

    private var fragmentInteractor: FragmentInteractor? = null
}