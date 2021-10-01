package com.example.rickandmorty.ui.activity.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickandmorty.ui.fragment.FavoriteFragment
import com.example.rickandmorty.ui.fragment.HomeFragment
import com.example.rickandmorty.ui.fragment.SavedFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            2 -> SavedFragment()
            else -> HomeFragment()
        }
    }
}