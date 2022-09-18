package com.example.rickandmorty.ui.fragment

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
        super.setupBindings()
        recyclerAdapter.setupDiffUtil(CharacterDiffCallback())
        binding.rfRvPersons.adapter = recyclerAdapter
        binding.button.setOnClickListener {
            viewModel.test2()

        }
        viewModel.mutableLiveData.observe(this) {
            recyclerAdapter.items = it ?: listOf()
        }
    }
}