package com.example.rickandmorty.ui.fragment

import androidx.core.widget.doAfterTextChanged
import com.example.rickandmorty.R
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.recycler.CharacterDiffCallback
import com.example.rickandmorty.ui.viewmodel.HomeViewModel

class HomeFragment : BaseCharacterFragment<HomeViewModel>(R.layout.recycler_fragment) {

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
//        TODO поработать над clickListeners
        binding.search.doAfterTextChanged { text ->
            viewModel.getPersonsOnSearch(text.toString())
        }
        binding.retry.setOnClickListener {
            viewModel.loadData(true)
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

    override fun onLikeClickListener(person: Person) {
        viewModel.like(person)
    }

    override fun onDeleteClickListener(person: Person) {
        viewModel.delete(person)
    }
}