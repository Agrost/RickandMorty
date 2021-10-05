package com.example.rickandmorty.ui.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.data.toCharacterList
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import com.example.rickandmorty.ui.viewmodel.FavoriteViewModel

class FavoriteFragment : BaseFragment() {

    private lateinit var viewModel: FavoriteViewModel

    override fun isFavorite(): Boolean = true

    override fun initDagger() {
        requireActivity().appComponent
            .registerFavoriteComponent()
            .create()
            .inject(this)
    }

    override fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataByName(searchText)
    }

    override fun getDataOnScroll() {}

    override fun getDataOnSearch(text: String) {
        viewModel.getDataByName(text)
        super.getDataOnSearch(text)
    }

    override fun getDataOnRetry() {
        viewModel.getDataByName(searchText)
    }

    override fun observeViewModel(recyclerAdapter: RecyclerAdapter) {
        viewModel.answer.observe(viewLifecycleOwner) {
            showView(it)
        }
    }

    override fun addToFavorite(character: Character) {}

    override fun deleteFromFavorite(id: Int) {
        viewModel.deleteFromFavorite(id)
    }
}