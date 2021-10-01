package com.example.rickandmorty.ui.fragment

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.appComponent
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import com.example.rickandmorty.ui.viewmodel.HomeViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable.create
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.util.*
import java.util.concurrent.TimeUnit

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
        viewModel.getFirstPage(text)
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