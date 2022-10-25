package com.example.rickandmorty.ui.fragment

import androidx.annotation.LayoutRes
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.databinding.RecyclerFragmentBinding
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.activity.interactor.FragmentInteractor
import com.example.rickandmorty.ui.base.BaseFragment
import com.example.rickandmorty.ui.recycler.PersonAdapter
import com.example.rickandmorty.ui.viewmodel.BasePersonListViewModel

abstract class BaseCharacterFragment<VM : BasePersonListViewModel>(@LayoutRes contentLayoutId: Int) :
    BaseFragment<VM>(contentLayoutId) {

    protected var recyclerAdapter: PersonAdapter = PersonAdapter(
        onDeleteClickListener = { person -> onDeleteClickListener(person) },
        onLikeClickListener = { person -> onLikeClickListener(person) },
        onPhotoClickListener = { src -> onPhotoClickListener(src) }
    )

    protected val binding by viewBinding(RecyclerFragmentBinding::bind)

    private var fragmentInteractor: FragmentInteractor? = null

    override fun onDestroyView() {
//      TODO
//      recyclerAdapter = null
        super.onDestroyView()
    }

    protected open fun onDeleteClickListener(person: Person) = Unit
    protected open fun onLikeClickListener(person: Person) = Unit
    private fun onPhotoClickListener(src: String) {
//        TODO
    }
}
