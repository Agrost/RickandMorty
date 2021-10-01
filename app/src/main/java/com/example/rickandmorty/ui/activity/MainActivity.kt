package com.example.rickandmorty.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.ui.activity.interactor.FragmentInteractor
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.MainActivityBinding
import com.example.rickandmorty.ui.activity.pageradapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity: AppCompatActivity(R.layout.main_activity), FragmentInteractor {

    private val viewBinding by viewBinding(MainActivityBinding::bind)

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_NAME = "CHARACTER_NAME"
        const val CHARACTER_SRC = "CHARACTER_SRC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val activity = this
        with(viewBinding) {
            pager.adapter = ViewPagerAdapter(activity)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                when (position) {
                    0 -> tab.setText(R.string.characters)
                    1 -> tab.setText(R.string.favorite)
                    2 -> tab.setText(R.string.saved)
                    else -> tab.setText(R.string.characters)
                }
            }.attach()
        }
    }

    override fun openImageFragment(character: Character) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(CHARACTER_ID, character.id)
        intent.putExtra(CHARACTER_NAME, character.name)
        intent.putExtra(CHARACTER_SRC, character.imageSrc)
        startActivity(intent)
    }
}