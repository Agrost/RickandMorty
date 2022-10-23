package com.example.rickandmorty.ui.viewmodel

import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.domain.entity.Person
import com.example.rickandmorty.ui.EMPTY_STRING
import com.example.rickandmorty.ui.recycler.PersonRecyclerAdapter
import com.facebook.shimmer.ShimmerFrameLayout

sealed interface RecyclerFragmentsState {

    fun apply(
        progressBar: ProgressBar,
        shimmerFrameLayout: ShimmerFrameLayout,
        recyclerView: RecyclerView,
        errorView: LinearLayout,
        recyclerAdapter: PersonRecyclerAdapter
    )

    abstract class Abstract(
        private val shimmerVisibility: Boolean = false,
        private val recyclerVisibility: Boolean = false,
        private val progressBarVisibility: Boolean = false,
        private val errorVisibility: Boolean = false
    ) : RecyclerFragmentsState {

        override fun apply(
            progressBar: ProgressBar,
            shimmerFrameLayout: ShimmerFrameLayout,
            recyclerView: RecyclerView,
            errorView: LinearLayout,
            recyclerAdapter: PersonRecyclerAdapter
        ) {
            shimmerFrameLayout.isVisible = shimmerVisibility
            progressBar.isVisible = progressBarVisibility
            recyclerView.isVisible = recyclerVisibility
            errorView.isVisible = errorVisibility
        }
    }

    object Loading : Abstract(shimmerVisibility = true)

    class Success(val data: List<Person>) :
        Abstract(recyclerVisibility = true) {

        override fun apply(
            progressBar: ProgressBar,
            shimmerFrameLayout: ShimmerFrameLayout,
            recyclerView: RecyclerView,
            errorView: LinearLayout,
            recyclerAdapter: PersonRecyclerAdapter
        ) {
            super.apply(progressBar, shimmerFrameLayout, recyclerView, errorView, recyclerAdapter)

            recyclerAdapter.items = data
            recyclerView.scrollToPosition(0)
        }
    }

    object Update : Abstract(recyclerVisibility = true, progressBarVisibility = true)

    class Error(val errorData: String = EMPTY_STRING) : Abstract(errorVisibility = true)
}