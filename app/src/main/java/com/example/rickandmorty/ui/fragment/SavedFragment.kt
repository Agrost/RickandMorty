package com.example.rickandmorty.ui.fragment

import com.example.rickandmorty.R
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.ui.viewmodel.SavedViewModel

class SavedFragment : BaseCharacterFragment<SavedViewModel>(R.layout.recycler_fragment) {

    override val viewModelType: Class<SavedViewModel> = SavedViewModel::class.java

    override fun initDagger() {
        requireActivity().appComponent
            .registerSavedComponent()
            .create()
            .inject(this)
    }
}