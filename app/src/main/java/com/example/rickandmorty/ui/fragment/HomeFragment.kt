package com.example.rickandmorty.ui.fragment

import androidx.core.widget.doAfterTextChanged
import com.example.rickandmorty.R
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.ui.recycler.CharacterDiffCallback
import com.example.rickandmorty.ui.viewmodel.HomeViewModel

class HomeFragment : BaseRecyclerFragment<HomeViewModel>(R.layout.recycler_fragment) {

    override val viewModelType: Class<HomeViewModel> = HomeViewModel::class.java

    override fun initDagger() {
        requireActivity().appComponent
            .registerHomeComponent()
            .create()
            .inject(this)
    }

    override fun setupBindings() {
        recyclerAdapter.setupDiffUtil(CharacterDiffCallback())
        binding.characterRecycler.adapter = recyclerAdapter
        binding.search.doAfterTextChanged { text ->
            viewModel.getPersonsOnSearch(text.toString())
        }
    }

    override fun observeViewModel() {
        viewModel.observe(this) { state ->
            state.apply(
                binding.progressBar,
                binding.recyclerShimmer,
                binding.characterRecycler,
                binding.errorContainer,
                recyclerAdapter
            )
        }
    }
}