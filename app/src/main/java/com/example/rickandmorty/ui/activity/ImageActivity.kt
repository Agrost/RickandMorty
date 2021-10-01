package com.example.rickandmorty.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ImageActivityBinding

class ImageActivity : AppCompatActivity(R.layout.image_activity) {

    private val viewBinding by viewBinding(ImageActivityBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_activity)
        val activity = this
        intent?.let {
            with(viewBinding) {
                val characterId = intent.getIntExtra(MainActivity.CHARACTER_ID, 0)
                val characterIdText = getString(R.string.id_text, characterId.toString())
                val characterImage = intent.getStringExtra(MainActivity.CHARACTER_SRC)

                this.characterId.text = characterIdText
                this.characterName.text = intent.getStringExtra(MainActivity.CHARACTER_NAME)
                activity.let { context ->
                    Glide.with(context)
                        .load(characterImage)
                        .placeholder(R.drawable.animview)
                        .into(this.characterImage)
                }
            }
        }
    }
}