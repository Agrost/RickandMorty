package com.example.rickandmorty.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Answer
import com.example.rickandmorty.databinding.RecyclerFragmentBinding
import com.example.rickandmorty.di.DaggerViewModelFactory
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.activity.interactor.FragmentInteractor
import com.example.rickandmorty.ui.recycler.RecyclerAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseFragment : Fragment(R.layout.recycler_fragment) {

    @Inject
    protected lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewBinding by viewBinding(RecyclerFragmentBinding::bind)
    private var firstLaunch = true
    protected var searchText = ""
    private var fragmentInteractor: FragmentInteractor? = null
    private var recyclerAdapter: RecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as? FragmentInteractor)?.let {
            fragmentInteractor = it
        }
        super.onViewCreated(view, savedInstanceState)
        initDagger()
        initViewModule()
        addBindings()
    }

    abstract fun isFavorite(): Boolean

    abstract fun initDagger()

    abstract fun initViewModule()

    abstract fun getDataOnScroll()

    protected open fun getDataOnSearch(text: String) {
        with(viewBinding) {
            val layoutManager = characterRecycler.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(0, 0)
        }
    }

    abstract fun getDataOnRetry()

    abstract fun observeViewModel(recyclerAdapter: RecyclerAdapter)

    abstract fun addToFavorite(character: Character)

    abstract fun deleteFromFavorite(id: Int)

    private fun getRecycler(): RecyclerAdapter {
        recyclerAdapter = RecyclerAdapter(
            openImageActivity = { fragmentInteractor?.openImageFragment(it) },
            addToFavorite = { addToFavorite(it) },
            deleteFromFavorite = { deleteFromFavorite(it) },
            isFavorite = isFavorite()
        )
        observeViewModel(recyclerAdapter!!)
        return recyclerAdapter as RecyclerAdapter
    }

    private fun hideAllViews() {
        with(viewBinding) {
            characterRecycler.isVisible = false
            recyclerProgressBar.isVisible = false
            recyclerShimmer.isVisible = false
            errorContainer.isVisible = false
        }
    }

    private fun addBindings() {
        with(viewBinding) {
            characterRecycler.apply {
                adapter = getRecycler()
                addOnScrollListener(getScrollListener())
            }
            observeSearchView(searchRecycler)
            retryBtn.setOnClickListener { getDataOnRetry() }
        }
    }

    private fun observeSearchView(searchView: SearchView) {
        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    subscriber.onNext(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }
            })
        }).map { text -> text.lowercase(Locale.getDefault()).trim() }
            .debounce(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchText = it
                getDataOnSearch(it)
            }
    }

    private fun getScrollListener(): RecyclerView.OnScrollListener {
        return object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    getDataOnScroll()
                }
            }
        }
    }

    protected fun showView(answer: Answer) {
        hideAllViews()
        with(viewBinding) {
            when (answer) {
                is Answer.Loading -> {
                    recyclerShimmer.isVisible = firstLaunch
                    recyclerProgressBar.isVisible = true
                    characterRecycler.isVisible = true
                    firstLaunch = false
                }
                is Answer.Success -> {
                    recyclerAdapter?.items = answer.listCharacter.toMutableList()
                    characterRecycler.isVisible = true
                }
                is Answer.Failure -> {
                    if (answer.getNextPage) {
                        characterRecycler.isVisible = true
                    } else {
                        errorContainer.isVisible = true
                    }
                }
            }
        }
    }
}