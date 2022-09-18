package com.example.rickandmorty.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VM>

    protected abstract val viewModelType: Class<VM>

    protected val viewModel: VM by lazy { getViewModelInstance() }

    protected open fun getViewModelInstance(): VM =
        ViewModelProvider(this as Fragment, viewModelFactory)[viewModelType]

    override fun onAttach(context: Context) {
        initDagger()
        super.onAttach(context)
    }

    protected abstract fun initDagger()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings()
    }

    protected open fun setupBindings() {}
}