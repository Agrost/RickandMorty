package com.example.rickandmorty.ui.fragment

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import com.example.rickandmorty.ui.viewmodel.SavedViewModel

class SavedFragment : BaseFragment() {

    private lateinit var viewModel: SavedViewModel

    override fun isFavorite(): Boolean = false

    override fun initDagger() {
        requireActivity().appComponent
            .registerSavedComponent()
            .create()
            .inject(this)
    }

    override fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[SavedViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataByName(searchText)
    }

    override fun getDataOnScroll() {
        viewModel.getDataByName(searchText)
    }

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

    override fun addToFavorite(character: Character) {
        viewModel.addToFavorite(character)
    }

    override fun deleteFromFavorite(id: Int) {}
}