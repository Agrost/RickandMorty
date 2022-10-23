package com.example.rickandmorty.ui.fragment

import com.example.rickandmorty.R
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.ui.viewmodel.FavoriteViewModel

class FavoriteFragment : BaseRecyclerFragment<FavoriteViewModel>(R.layout.recycler_fragment) {

    override val viewModelType: Class<FavoriteViewModel> = FavoriteViewModel::class.java

    override fun initDagger() {
        requireActivity().appComponent
            .registerFavoriteComponent()
            .create()
            .inject(this)
    }

    override fun setupBindings() {
    }

    override fun onResume() {
        super.onResume()
        binding.button.setOnClickListener {  }
    }
}