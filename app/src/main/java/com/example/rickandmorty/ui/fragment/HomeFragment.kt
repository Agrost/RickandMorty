package com.example.rickandmorty.ui.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import com.example.rickandmorty.ui.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    override fun isFavorite(): Boolean = false

    override fun initDagger() {
        requireActivity().appComponent
            .registerHomeComponent()
            .create()
            .inject(this)
    }

    override fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun getDataOnScroll() {
        viewModel.getNextPage()
    }

    override fun getDataOnSearch(text: String) {
        viewModel.getDataByName(text)
        super.getDataOnSearch(text)
    }

    override fun getDataOnRetry() {
        viewModel.getData()
    }

    override fun observeViewModel(recyclerAdapter: RecyclerAdapter) {
        viewModel.answer.observe(viewLifecycleOwner) {
            showView(it)
        }
    }

    override fun addToFavorite(character: Character) {
        viewModel.addToFavorite(character)
    }

    override fun deleteFromFavorite(id: Int) {}
}